package com.ivantrogrlic.leaguestats.dagger

import com.ivantrogrlic.leaguestats.main.MainActivity
import com.ivantrogrlic.leaguestats.main.summoner.SummonerActivity
import com.ivantrogrlic.leaguestats.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(FragmentBindingModule::class)])
    @PerActivity
    abstract fun contributeHomeActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(FragmentBindingModule::class)])
    @PerActivity
    abstract fun contributeSummonerActivity(): SummonerActivity

    @ContributesAndroidInjector
    @PerActivity
    abstract fun contributeSplashScreenActivity(): SplashScreenActivity

}
