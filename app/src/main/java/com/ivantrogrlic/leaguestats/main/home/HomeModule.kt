package com.ivantrogrlic.leaguestats.main.home

import android.content.Context
import android.content.SharedPreferences
import com.ivantrogrlic.leaguestats.dagger.ApplicationContext
import com.ivantrogrlic.leaguestats.dagger.PerFragment
import com.ivantrogrlic.leaguestats.web.RiotWebService
import com.ivantrogrlic.leaguestats.web.RiotWebServiceModule
import dagger.Module
import dagger.Provides

@Module(includes = [RiotWebServiceModule::class])
class HomeModule {

    @Provides
    @PerFragment
    internal fun homePresenter(@ApplicationContext context: Context,
                               riotWebService: RiotWebService,
                               sharedPreferences: SharedPreferences): HomePresenter {
        return HomePresenter(context, sharedPreferences, riotWebService)
    }

    @Provides
    @PerFragment
    internal fun provideHomeFragmentView(detailFragment: HomeFragment): HomeView {
        return detailFragment
    }

}
