package com.ivantrogrlic.leaguestats.main.summoner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.model.Summoner
import com.ivantrogrlic.leaguestats.web.getProfileIconUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.summoner_screen.*
import org.parceler.Parcels

/**
 * Created by ivanTrogrlic on 19/07/2017.
 */

class SummonerActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.summoner_screen)
    
    val summoner = Parcels.unwrap<Summoner>(intent.getParcelableExtra(HomeFragment.SUMMONER_KEY))
    setupSummonerView(summoner)
  }
  
  private fun setupSummonerView(summoner: Summoner) {
    Picasso.with(this).load(getProfileIconUrl(summoner.profileIconId)).into(summoner_icon)
    summoner_name.text = summoner.name
    summoner_level.text = resources.getString(R.string.summoner_level, summoner.summonerLevel)
  }
  
}
