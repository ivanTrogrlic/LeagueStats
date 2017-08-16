package com.ivantrogrlic.leaguestats.model

import android.support.annotation.DrawableRes
import com.ivantrogrlic.leaguestats.R

/**
 * Created by ivanTrogrlic on 20/07/2017.
 */

data class LeaguePosition(val rank: String,
                          val queueType: String,
                          val hotStreak: Boolean,
                          val miniSeries: MiniSeries,
                          val wins: Int,
                          val veteran: Boolean,
                          val losses: Int,
                          val playerOrTeamId: String,
                          val leagueName: String,
                          val playerOrTeamName: String,
                          val inactive: Boolean,
                          val freshBlood: Boolean,
                          val tier: String,
                          val leaguePoints: Int) {
  
  fun isRankedSolo5v5() = "RANKED_SOLO_5x5" == queueType
  fun isRankedFlex5v5() = "RANKED_FLEX_SR" == queueType
  fun isRankedTeam3v3() = "RANKED_FLEX_TT" == queueType
  
  @DrawableRes fun getTierIcon(): Int {
    return when (tier) {
      "BRONZE" -> R.drawable.ic_bronze
      "SILVER" -> R.drawable.ic_silver
      "GOLD" -> R.drawable.ic_gold
      "PLATINUM" -> R.drawable.ic_platinum
      "DIAMOND" -> R.drawable.ic_diamond
      "MASTER" -> R.drawable.ic_master
      "CHALLENGER" -> R.drawable.ic_challenger
      else -> R.drawable.ic_unranked
    }
  }
  
}
