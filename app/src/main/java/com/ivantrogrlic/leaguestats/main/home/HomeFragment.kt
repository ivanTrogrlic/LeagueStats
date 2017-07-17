package com.ivantrogrlic.leaguestats.main.home

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.model.Summoner
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

class HomeFragment : MvpFragment<HomeView, HomePresenter>(), HomeView {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }
  
  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.home_fragment, container, false)
  
  override fun onResume() {
    super.onResume()
    search.setOnClickListener {
      (search.drawable as Animatable).start()
      getPresenter().searchForSummoner(summoner_input.text.toString())
    }
  }
  
  override fun createPresenter(): HomePresenter {
    val application = activity.application as LeagueStatsApplication
    return HomePresenter(application.component().context(),
                         application.component().sharedPreferences(),
                         application.netComponent()!!.retrofit())
  }
  
  override fun setHint(text: String) {
    hint.text = text
  }
  
  override fun summonerLoaded(summoner: Summoner) {
    
  }
  
  override fun searchingFailed() {
    val create = AnimatedVectorDrawableCompat.create(context, R.drawable.search_animation_stop)
    search.setImageDrawable(create)
    (create as Animatable).start()
  }
  
}