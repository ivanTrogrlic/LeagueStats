package com.ivantrogrlic.leaguestats.main.home

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.ivantrogrlic.leaguestats.model.Summoner

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */
interface HomeView : MvpView {
  fun setHint(text: String)
  fun summonerLoaded(summoner: Summoner)
  fun searchingFailed()
  fun summonerNotFound()
}
