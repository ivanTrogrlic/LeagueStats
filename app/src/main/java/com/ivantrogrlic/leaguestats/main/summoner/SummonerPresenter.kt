package com.ivantrogrlic.leaguestats.main.summoner

import android.content.Context
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.web.RiotWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by ivanTrogrlic on 19/07/2017.
 */
class SummonerPresenter(val context: Context,
                        val retrofit: Retrofit) : MvpBasePresenter<SummonerView>() {
  
  fun getLeaguePositions(summonerId: Long) {
    retrofit.create(RiotWebService::class.java)
        .leaguePositions(summonerId)
        .firstOrError()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ view.showLeaguePositions(it) },
                   { Log.e("Trogy", "Failed loading league positions", it) })
  }
  
}
