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
    search.setOnClickListener { (search.drawable as Animatable).start() }
    summoner_input.setOnClickListener {
      val create = AnimatedVectorDrawableCompat.create(context, R.drawable.search_animation_stop)
      search.setImageDrawable(create)
      (create as Animatable).start()
    }
  }
  
  override fun createPresenter(): HomePresenter {
    return HomePresenter((activity.application as LeagueStatsApplication).netComponent()!!.retrofit())
  }
  
  override fun loadSummoner() {
    
  }
  
}