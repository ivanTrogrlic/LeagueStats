package com.ivantrogrlic.leaguestats.dagger

import com.ivantrogrlic.leaguestats.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/** Created by ivanTrogrlic on 12/07/2017. */

@Module
abstract class ActivitiesModule {
  
  @ContributesAndroidInjector()
  abstract fun provideMainActivityInjector(): MainActivity
  
}
