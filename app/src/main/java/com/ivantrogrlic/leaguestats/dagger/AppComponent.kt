package com.ivantrogrlic.leaguestats.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.web.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/** Created by ivanTrogrlic on 12/07/2017. */

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (AppModule::class),
    (NetModule::class),
    (ActivityBindingModule::class)])
interface AppComponent : AndroidInjector<LeagueStatsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: LeagueStatsApplication)

    @ApplicationContext
    fun context(): Context

    fun sharedPreferences(): SharedPreferences

}
