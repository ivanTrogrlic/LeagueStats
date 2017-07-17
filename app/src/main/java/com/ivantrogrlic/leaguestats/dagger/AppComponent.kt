package com.ivantrogrlic.leaguestats.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.rest.NetComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/** Created by ivanTrogrlic on 12/07/2017. */

@Singleton
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
  
  fun netComponentBuilder(): NetComponent.Builder
  
  fun context(): Context
  
  fun sharedPreferences(): SharedPreferences
  
}
