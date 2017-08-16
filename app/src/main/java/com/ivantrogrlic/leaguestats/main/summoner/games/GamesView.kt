package com.ivantrogrlic.leaguestats.main.summoner.games

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.ivantrogrlic.leaguestats.model.Match
import java.util.*

/**
 * Created by ivan on 8/9/2017.
 */
interface GamesView : MvpView {
    fun setMatches(matches: List<Match>)
}
