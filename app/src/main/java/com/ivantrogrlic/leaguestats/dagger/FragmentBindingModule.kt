package com.ivantrogrlic.leaguestats.dagger

import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.main.summoner.games.GamesFragment
import com.ivantrogrlic.leaguestats.main.summoner.ranks.RanksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector()
    @PerFragment
    abstract fun provideHomeFragmentFactory(): HomeFragment

    @ContributesAndroidInjector()
    @PerFragment
    abstract fun provideGamesFragmentFactory(): GamesFragment

    @ContributesAndroidInjector()
    @PerFragment
    abstract fun provideRanksFragmentFactory(): RanksFragment

}
