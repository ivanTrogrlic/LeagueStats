package com.ivantrogrlic.leaguestats.main

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.ivantrogrlic.leaguestats.R
import dagger.android.AndroidInjection

/**
 * Created by ivanTrogrlic on 12/07/2017.
 */

public class MainActivity : MainView, MvpActivity<MainView, MainPresenter>() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_screen)
  }
  
  override fun doSomethig() {
  }
  
  override fun createPresenter() = MainPresenter()
  
}
