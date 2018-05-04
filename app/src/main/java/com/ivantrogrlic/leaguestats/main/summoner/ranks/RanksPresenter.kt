package com.ivantrogrlic.leaguestats.main.summoner.ranks

import android.content.Context
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.dagger.ApplicationContext
import com.ivantrogrlic.leaguestats.dagger.PerFragment
import com.ivantrogrlic.leaguestats.web.RiotWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ivan on 8/9/2017.
 */

@PerFragment
class RanksPresenter @Inject constructor(@ApplicationContext private val context: Context,
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
