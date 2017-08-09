package com.ivantrogrlic.leaguestats.main.summoner.ranks

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.ivantrogrlic.leaguestats.model.LeaguePosition

/**
 * Created by ivan on 8/9/2017.
 */
interface RanksView : MvpView {
    fun showLeaguePositions(leaguePositions: Set<LeaguePosition>)
}
