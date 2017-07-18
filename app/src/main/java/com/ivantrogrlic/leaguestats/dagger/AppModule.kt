package com.ivantrogrlic.leaguestats.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ivantrogrlic.leaguestats.rest.NetComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** Created by ivanTrogrlic on 12/07/2017. */

@Module(subcomponents = arrayOf(NetComponent::class))
class AppModule {
  
  @Singleton
  @Provides
  @ApplicationContext
  fun provideContext(application: Application): Context = application.applicationContext
  
  @Provides
  @Singleton
  fun sharedPreferencesName(): String = "LeagueStatsPrefs"
  
  @Provides
  @Singleton
  fun sharedPreferences(@ApplicationContext context: Context,
                        sharedPreferencesName: String): SharedPreferences =
      context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
  
}
