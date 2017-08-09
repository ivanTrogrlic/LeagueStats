package com.ivantrogrlic.leaguestats.main.summoner.ranks

import android.content.Context
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.web.RiotWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by ivan on 8/9/2017.
 */
class RanksPresenter(val context: Context,
                     val retrofit: Retrofit) : MvpBasePresenter<RanksView>() {

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
