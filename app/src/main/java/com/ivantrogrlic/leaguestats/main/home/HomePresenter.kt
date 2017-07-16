package com.ivantrogrlic.leaguestats.main.home

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.rest.RiotWebService
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

class HomePresenter constructor(val retrofit: Retrofit) : MvpBasePresenter<HomeView>() {
  
  override fun attachView(view: HomeView?) {
    super.attachView(view)
    
    val riotWebService = retrofit.create(RiotWebService::class.java)
    riotWebService
        .summoner("vindfaker9")
        .firstOrError()
        .subscribeOn(Schedulers.io())
        .subscribe({ Log.d("Trogy", it.toString()) }, { Log.d("Trogy", it.toString()) })
  }
}
