package com.ivantrogrlic.leaguestats.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ivantrogrlic.leaguestats.main.home.HomeFragment

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

class MainScreenAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
  
  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> HomeFragment()
      1 -> HomeFragment() //TODO
      2 -> HomeFragment() //TODO
      else -> throw IllegalStateException("Illegal pager position: $position")
    }
  }
  
  override fun getCount(): Int = 3
  
}
