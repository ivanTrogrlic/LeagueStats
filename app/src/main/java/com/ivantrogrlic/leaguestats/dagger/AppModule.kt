package com.ivantrogrlic.leaguestats.dagger

import android.app.Application
import android.content.Context
import com.ivantrogrlic.leaguestats.rest.NetComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** Created by ivanTrogrlic on 12/07/2017. */

@Module(subcomponents = arrayOf(NetComponent::class))
class AppModule {
  
  @Provides
  @Singleton
  fun provideContext(application: Application): Context = application
  
}
