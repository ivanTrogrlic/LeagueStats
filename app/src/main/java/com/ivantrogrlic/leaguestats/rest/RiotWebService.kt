package com.ivantrogrlic.leaguestats.rest

import com.ivantrogrlic.leaguestats.model.Summoner
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by ivanTrogrlic on 16/07/2017.
 */

interface RiotWebService {
  // TODO move query param to interceptor
  @GET("lol/summoner/v3/summoners/by-name/{summonerName}")
  fun summoner(@Path("summonerName") summonerName: String,
               @Query("api_key") apiKey: String): Flowable<Summoner>
}
