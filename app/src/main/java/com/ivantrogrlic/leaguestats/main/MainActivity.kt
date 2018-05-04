package com.ivantrogrlic.leaguestats.main

import android.os.Bundle
import com.ivantrogrlic.leaguestats.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_screen.*


/**
 * Created by ivanTrogrlic on 12/07/2017.
 */

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        val adapter = MainScreenAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

}
