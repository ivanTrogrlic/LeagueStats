package com.ivantrogrlic.leaguestats.web

import com.ivantrogrlic.leaguestats.model.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by ivanTrogrlic on 16/07/2017.
 */

interface RiotWebService {
    @GET("lol/summoner/v3/summoners/by-name/{summonerName}")
    fun summoner(@Path("summonerName") summonerName: String): Flowable<Summoner>

    @GET("/lol/league/v3/positions/by-summoner/{summonerId}")
    fun leaguePositions(@Path("summonerId") summonerId: Long): Flowable<Set<LeaguePosition>>

    @GET("/lol/match/v3/matchlists/by-account/{accountId}/recent")
    fun recentMatches(@Path("accountId") accountId: Long): Flowable<MatchList>

    @GET("/lol/match/v3/matches/{matchId}")
    fun match(@Path("matchId") matchId: Long, @Query("forAccountId") accountId: Long): Flowable<Match>

    @GET("/lol/static-data/v3/champions/{championId}")
    fun champion(@Path("championId") championId: Int): Flowable<Champion>

    @GET("/lol/static-data/v3/summoner-spells/{summonerSpellId}")
    fun summonerSpell(@Path("summonerSpellId") summonerSpellId: Int): Flowable<SummonerSpell>
}
