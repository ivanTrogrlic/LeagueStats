package com.ivantrogrlic.leaguestats.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivantrogrlic.leaguestats.R
import kotlinx.android.synthetic.main.main_screen.*


/**
 * Created by ivanTrogrlic on 12/07/2017.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        val adapter = MainScreenAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

}
