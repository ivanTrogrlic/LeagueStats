package com.ivantrogrlic.leaguestats

import android.app.Activity
import android.app.Application
import com.ivantrogrlic.leaguestats.dagger.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/** Created by ivanTrogrlic on 12/07/2017. */
class LeagueStatsApplication : Application(), HasActivityInjector {
  
  @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
  
  override fun onCreate() {
    super.onCreate()
    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this)
    
  }
  
  override fun activityInjector(): DispatchingAndroidInjector<Activity> {
    return activityDispatchingAndroidInjector
  }
  
}
