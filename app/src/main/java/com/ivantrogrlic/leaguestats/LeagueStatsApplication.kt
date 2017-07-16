package com.ivantrogrlic.leaguestats

import android.app.Activity
import android.app.Application
import com.ivantrogrlic.leaguestats.dagger.AppComponent
import com.ivantrogrlic.leaguestats.dagger.DaggerAppComponent
import com.ivantrogrlic.leaguestats.rest.NetComponent
import com.ivantrogrlic.leaguestats.rest.NetModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/** Created by ivanTrogrlic on 12/07/2017. */
class LeagueStatsApplication : Application(), HasActivityInjector {
  
  companion object {
    lateinit var appComponent: AppComponent
    var netComponent: NetComponent? = null
  }
  
  @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
  
  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent
        .builder()
        .application(this)
        .build()
    appComponent.inject(this)
    
  }
  
  override fun activityInjector(): DispatchingAndroidInjector<Activity> {
    return activityDispatchingAndroidInjector
  }
  
  fun component() = appComponent
  fun netComponent() = netComponent
  
  fun createNetComponent(baseUrl: String): NetComponent {
    netComponent = component()
        .netComponentBuilder()
        .netModule(NetModule(baseUrl))
        .build()
    return netComponent!!
  }
  
  fun destroyNetComponent() {
    netComponent = null
  }
  
}
