package com.ivantrogrlic.leaguestats.main.summoner

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.model.LeaguePosition
import com.ivantrogrlic.leaguestats.model.Summoner
import com.ivantrogrlic.leaguestats.web.getProfileIconUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rank_item.view.*
import kotlinx.android.synthetic.main.summoner_screen.*
import org.parceler.Parcels

/**
 * Created by ivanTrogrlic on 19/07/2017.
 */

class SummonerActivity : SummonerView, MvpActivity<SummonerView, SummonerPresenter>() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val summoner = Parcels.unwrap<Summoner>(intent.getParcelableExtra(HomeFragment.SUMMONER_KEY))
    setContentView(R.layout.summoner_screen)
    setupSummonerView(summoner)
    setupLeaguePositionViews()
    presenter.getLeaguePositions(summoner.id)
  }
  
  override fun createPresenter(): SummonerPresenter {
    val application = application as LeagueStatsApplication
    return SummonerPresenter(application.component().context(),
                             application.netComponent()!!.retrofit())
  }
  
  override fun showLeaguePositions(leaguePositions: Set<LeaguePosition>) {
    leaguePositions.forEach {
      if (it.isRankedSolo5v5()) setupLeaguesView(it, ranked_solo_card)
      if (it.isRankedFlex5v5()) setupLeaguesView(it, team_flex_card)
      if (it.isRankedTeam3v3()) setupLeaguesView(it, three_vs_three_card)
    }
  }
  
  private fun setupLeaguesView(it: LeaguePosition, cardView: View) {
    cardView.rank_icon.setImageResource(it.getTierIcon())
    cardView.rank.text = getString(R.string.tier_placeholder, it.tier, it.rank)
    cardView.league_points.text = getString(R.string.league_points_placeholder, it.leaguePoints)
    cardView.wins_losses.text = getString(R.string.wins_losses_placeholder, it.wins, it.losses)
    cardView.rank_name.text = it.leagueName
    
    val winPercentage = calculateWinRatio(it)
    var textColor = ContextCompat.getColor(this, R.color.gray)
    if (winPercentage > 50) textColor = ContextCompat.getColor(this, R.color.green)
    if (winPercentage > 60) textColor = ContextCompat.getColor(this, R.color.light_red)
    
    cardView.win_loss_ratio.setTextColor(textColor)
    cardView.win_loss_ratio.text = getString(R.string.win_ratio_placeholder, winPercentage)
  }
  
  private fun setupSummonerView(summoner: Summoner) {
    Picasso.with(this).load(getProfileIconUrl(summoner.profileIconId)).into(summoner_icon)
    summoner_name.text = summoner.name
    summoner_level.text = resources.getString(R.string.summoner_level, summoner.summonerLevel)
  }
  
  private fun setupLeaguePositionViews() {
    ranked_solo_card.queue_type.setText(R.string.ranked_solo)
    team_flex_card.queue_type.setText(R.string.flex_5v5)
    three_vs_three_card.queue_type.setText(R.string.ranked_3v3)
  }
  
  private fun calculateWinRatio(it: LeaguePosition): Float {
    val totalGames = it.losses.toFloat() + it.wins.toFloat()
    val lossRatio = 1 - (it.losses / totalGames)
    val winPercentage = lossRatio * 100
    return winPercentage
  }
  
}
