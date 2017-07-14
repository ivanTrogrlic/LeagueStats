package com.ivantrogrlic.leaguestats.main.home

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import retrofit2.Retrofit

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

class HomePresenter constructor(val retrofit: Retrofit) : MvpBasePresenter<HomeView>() {
  
  override fun attachView(view: HomeView?) {
    super.attachView(view)
    Log.d("Trogy", retrofit.baseUrl().toString())
  }
}
