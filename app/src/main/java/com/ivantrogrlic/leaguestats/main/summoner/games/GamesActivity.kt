package com.ivantrogrlic.leaguestats.main.summoner.games

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.R

/**
 * Created by ivan on 8/9/2017.
 */

class GamesActivity : GamesView, MvpActivity<GamesView, GamesPresenter>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.summoner_screen)
    }

    override fun createPresenter(): GamesPresenter {
        val application = application as LeagueStatsApplication
        return GamesPresenter()
    }

    override fun test() {

    }

}
