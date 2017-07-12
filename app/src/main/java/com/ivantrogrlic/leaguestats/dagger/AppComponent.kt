package com.ivantrogrlic.leaguestats.dagger

import android.app.Application
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

/** Created by ivanTrogrlic on 12/07/2017. */

@Component(modules = arrayOf(AndroidInjectionModule::class,
                             AppModule::class,
                             ActivitiesModule::class))
interface AppComponent {
  
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder
    fun build(): AppComponent
  }
  
  fun inject(app: LeagueStatsApplication)
}
