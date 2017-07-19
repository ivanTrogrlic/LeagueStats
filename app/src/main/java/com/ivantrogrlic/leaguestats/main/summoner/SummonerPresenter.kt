package com.ivantrogrlic.leaguestats.main.summoner

import android.content.Context
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import retrofit2.Retrofit

/**
 * Created by ivanTrogrlic on 19/07/2017.
 */
class SummonerPresenter(context: Context, retrofit: Retrofit) : MvpBasePresenter<SummonerView>()
