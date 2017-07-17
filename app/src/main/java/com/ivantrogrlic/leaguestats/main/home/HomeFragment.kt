package com.ivantrogrlic.leaguestats.main.home

import android.os.Bundle
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
                            savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.home_fragment, container, false)
    
    search.setOnClickListener { getPresenter().searchForSummoner(summoner_input.text.toString()) }
    
    return view
  }
  
  
  override fun createPresenter(): HomePresenter {
    return HomePresenter((activity.application as LeagueStatsApplication).netComponent()!!.retrofit())
  }
  
  override fun loadSummoner() {
    
  }
  
}