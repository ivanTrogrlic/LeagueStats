package com.ivantrogrlic.leaguestats.main.summoner.ranks

import android.content.Context
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.web.RiotWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ivan on 8/9/2017.
 */

class RanksPresenter constructor(private val context: Context,
                                 private val riotWebService: RiotWebService)
    : MvpBasePresenter<RanksView>() {

    fun getLeaguePositions(summonerId: Long) =
            riotWebService
                    .leaguePositions(summonerId)
                    .firstOrError()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ view.showLeaguePositions(it) },
                            { Log.e("Trogy", "Failed loading league positions", it) })

}
