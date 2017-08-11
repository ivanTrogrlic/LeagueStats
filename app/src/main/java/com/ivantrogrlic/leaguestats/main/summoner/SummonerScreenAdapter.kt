package com.ivantrogrlic.leaguestats.main.summoner

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.main.summoner.games.GamesFragment
import com.ivantrogrlic.leaguestats.main.summoner.ranks.RanksFragment
import com.ivantrogrlic.leaguestats.model.Summoner
import org.parceler.Parcels

/**
 * Created by ivan on 8/9/2017.
 */

class SummonerScreenAdapter(manager: FragmentManager, val summoner: Summoner) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        val ranksFragment = RanksFragment()
        val bundle = Bundle()
        bundle.putParcelable(HomeFragment.SUMMONER_KEY, Parcels.wrap(summoner))
        ranksFragment.arguments = bundle
        return when (position) {
            0 -> ranksFragment
            1 -> GamesFragment()
            else -> throw IllegalStateException("Illegal pager position: $position")
        }
    }

    override fun getCount(): Int = 2

}
