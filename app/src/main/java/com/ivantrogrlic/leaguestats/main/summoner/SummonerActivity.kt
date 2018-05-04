package com.ivantrogrlic.leaguestats.main.summoner

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.model.Summoner
import com.ivantrogrlic.leaguestats.web.getProfileIconUrl
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.summoner_screen.*
import org.parceler.Parcels.unwrap


/**
 * Created by ivanTrogrlic on 19/07/2017.
 */

class SummonerActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summoner_screen)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.ranks_title))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.matches_title))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val summoner = unwrap<Summoner>(intent.getParcelableExtra(HomeFragment.SUMMONER_KEY))
        val adapter = SummonerScreenAdapter(supportFragmentManager, summoner)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(tabSelectedListener())

        setupSummonerView(summoner)
    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayout.clearOnTabSelectedListeners()
    }

    private fun tabSelectedListener() =
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            }

    private fun setupSummonerView(summoner: Summoner) {
        Picasso.get().load(getProfileIconUrl(summoner.profileIconId)).into(summoner_icon)
        summoner_name.text = summoner.name
        summoner_level.text = resources.getString(R.string.summoner_level, summoner.summonerLevel)
    }

}
