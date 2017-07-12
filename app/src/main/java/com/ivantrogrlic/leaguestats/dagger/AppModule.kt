package com.ivantrogrlic.leaguestats.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** Created by ivanTrogrlic on 12/07/2017. */

@Module
class AppModule {
  
  @Provides
  @Singleton
  fun provideContext(application: Application): Context {
    return application
  }
  
}
