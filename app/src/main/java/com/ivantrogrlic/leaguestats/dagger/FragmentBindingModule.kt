package com.ivantrogrlic.leaguestats.dagger

import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.main.home.HomeModule
import com.ivantrogrlic.leaguestats.main.summoner.games.GamesFragment
import com.ivantrogrlic.leaguestats.main.summoner.games.GamesModule
import com.ivantrogrlic.leaguestats.main.summoner.ranks.RanksFragment
import com.ivantrogrlic.leaguestats.main.summoner.ranks.RanksModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [(HomeModule::class)])
    @PerFragment
    abstract fun provideHomeFragmentFactory(): HomeFragment

    @ContributesAndroidInjector(modules = [GamesModule::class])
    @PerFragment
    abstract fun provideGamesFragmentFactory(): GamesFragment

    @ContributesAndroidInjector(modules = [RanksModule::class])
    @PerFragment
    abstract fun provideRanksFragmentFactory(): RanksFragment

}
