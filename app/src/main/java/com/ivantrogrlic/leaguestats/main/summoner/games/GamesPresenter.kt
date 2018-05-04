package com.ivantrogrlic.leaguestats.main.summoner.games

import android.content.Context
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.dagger.ApplicationContext
import com.ivantrogrlic.leaguestats.dagger.PerFragment
import com.ivantrogrlic.leaguestats.model.*
import com.ivantrogrlic.leaguestats.web.RiotWebService
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ivan on 8/9/2017.
 */

@PerFragment
class GamesPresenter @Inject constructor(@ApplicationContext private val context: Context,
                                         private val riotWebService: RiotWebService)
    : MvpBasePresenter<GamesView>() {

    fun getRecentMatches(summoner: Summoner) {
        riotWebService
                .recentMatches(summoner.accountId)
                .firstOrError()
                .map { listOf(it.matches.first()) }
                .flatMap { toMatches(it, summoner) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.setMatches(it) },
                        { Log.e("GamesPresenter", "Failed loading matches", it) })
    }

    private fun toMatches(matchReferences: List<MatchReference>, summoner: Summoner): Single<List<Match>> =
            Flowable.fromIterable(matchReferences)
                    .flatMap { riotWebService.match(it.gameId, summoner.accountId) }
                    .map { toMatchWithFilteredSummoner(it, summoner.id) }
                    .flatMap { toMatchWithIconNames(it) }
                    .toList()

    private fun toMatchWithFilteredSummoner(match: Match, summonerId: Long): Match {
        val participantId = match.participantIdentities
                .filter { it.player != null }
                .first { it.player!!.summonerId == summonerId }
                .participantId
        val participant = match.participants.first { it.participantId == participantId }
        return match.copy(participant = participant)
    }

    private fun toMatchWithIconNames(match: Match): Flowable<Match> {
        val participant = match.participant!!
        return Flowable.combineLatest(
                riotWebService.champion(participant.championId),
                riotWebService.summonerSpell(participant.spell1Id),
                riotWebService.summonerSpell(participant.spell2Id),
                Function3<Champion, SummonerSpell, SummonerSpell, Match> { (champion), (summoner1), (summoner2) ->
                    matchWithIconNames(match, participant, champion, summoner1, summoner2)
                }
        )
    }

    private fun matchWithIconNames(match: Match,
                                   participant: Participant,
                                   champion: String,
                                   summoner1: String,
                                   summoner2: String): Match =
            match
                    .copy(participants = listOf(
                            participant.copy(championName = champion,
                                    spell1Name = summoner1,
                                    spell2Name = summoner2)))

}
