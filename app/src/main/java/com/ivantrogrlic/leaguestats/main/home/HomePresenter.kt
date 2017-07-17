package com.ivantrogrlic.leaguestats.main.home

import android.content.Context
import android.content.SharedPreferences
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.model.ServiceProxy
import com.ivantrogrlic.leaguestats.rest.RiotWebService
import com.ivantrogrlic.leaguestats.util.Preferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.HttpException

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

class HomePresenter constructor(private val context: Context,
                                private val preferences: SharedPreferences,
                                retrofit: Retrofit) : MvpBasePresenter<HomeView>() {
  
  private var riotWebService: RiotWebService = retrofit.create(RiotWebService::class.java)
  
  override fun attachView(view: HomeView?) {
    super.attachView(view)
    
    val server = preferences.getString(Preferences.SERVER_PROXY_KEY, Preferences.NO_SERVER_SELECTED)
    val hint = context.getString(R.string.main_screen_subtitle,
                                 ServiceProxy.valueOf(server).serverName(context))
    getView().setHint(hint)
  }
  
  fun searchForSummoner(summoner: String) {
    riotWebService
        .summoner(summoner)
        .firstOrError()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ view.summonerLoaded(it) },
                   {
                     if (it is HttpException) {
                       view.searchingFailed()
                     }
                   })
  }
  
}
