package com.ivantrogrlic.leaguestats.main.home

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.summoner.SummonerActivity
import com.ivantrogrlic.leaguestats.model.Summoner
import kotlinx.android.synthetic.main.home_fragment.*
import org.parceler.Parcels

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

class HomeFragment : MvpFragment<HomeView, HomePresenter>(), HomeView {
  
  companion object {
    val SUMMONER_KEY = "SUMMONER_KEY"
  }
  
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
      startSearchAnimation(R.drawable.search_animation)
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
    val intent = Intent(context, SummonerActivity::class.java)
    intent.putExtra(SUMMONER_KEY, Parcels.wrap(Summoner::class.java, summoner))
    startActivity(intent)
    
    val create = AnimatedVectorDrawableCompat.create(context, R.drawable.search_animation)
    search.setImageDrawable(create)
  }
  
  override fun searchingFailed() {
    startSearchAnimation(R.drawable.search_animation_stop)
    summoner_input.error = context.getString(R.string.summoner_search_failed_general)
  }
  
  override fun summonerNotFound() {
    startSearchAnimation(R.drawable.search_animation_stop)
    summoner_input.error = context.getString(R.string.summoner_search_failed_not_found)
  }
  
  private fun startSearchAnimation(@DrawableRes animationId: Int) {
    val create = AnimatedVectorDrawableCompat.create(context, animationId)
    search.setImageDrawable(create)
    (create as Animatable).start()
  }
  
}
