package com.ivantrogrlic.leaguestats.main.summoner.games

import android.content.Context
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.model.Champion
import com.ivantrogrlic.leaguestats.model.Match
import com.ivantrogrlic.leaguestats.model.SummonerSpell
import com.ivantrogrlic.leaguestats.web.RiotWebService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by ivan on 8/9/2017.
 */

class GamesPresenter(val context: Context,
                     val retrofit: Retrofit) : MvpBasePresenter<GamesView>() {

    fun getLeaguePositions(summonerId: Int, accountId: Long) {
        val riotWebService = retrofit.create(RiotWebService::class.java)
        riotWebService
                .recentMatches(accountId)
                .firstOrError()
                .map { it.matches }
                .flatMap {
                    Flowable.fromIterable(it)
                            .flatMap { riotWebService.match(it.gameId) }
                            .map {
                                val participant = it.participants.first { it.participantId == summonerId }
                                it.copy(participants = listOf(participant))
                            }
                            .flatMap { match ->
                                Flowable.combineLatest(
                                        riotWebService.champion(match.getParticipant().championId),
                                        riotWebService.summonerSpell(match.getParticipant().spell1Id),
                                        riotWebService.summonerSpell(match.getParticipant().spell2Id),
                                        Function3<Champion, SummonerSpell, SummonerSpell, Match> {
                                            (champion), (summoner1), (summoner2) ->
                                            match.copy(participants =
                                            listOf(match.getParticipant().copy(
                                                    championName = champion,
                                                    spell1Name = summoner1,
                                                    spell2Name = summoner2)))
                                        }
                                )
                            }.toList()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ TODO() },
                        { Log.e("Trogy", "Failed loading league positions", it) })
    }

}
