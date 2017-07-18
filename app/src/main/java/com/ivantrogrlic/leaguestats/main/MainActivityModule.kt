package com.ivantrogrlic.leaguestats.main

import dagger.Module
import dagger.Provides

/**
 * Created by ivanTrogrlic on 12/07/2017.
 */

@Module
class MainActivityModule {
  
  @Provides
  fun provideMainPresenter(): MainPresenter = MainPresenter()
  
}
