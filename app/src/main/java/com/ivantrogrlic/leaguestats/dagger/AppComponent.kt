package com.ivantrogrlic.leaguestats.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.web.NetComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/** Created by ivanTrogrlic on 12/07/2017. */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: LeagueStatsApplication)

    @ApplicationContext
    fun context(): Context

    fun netComponentBuilder(): NetComponent.Builder

    fun sharedPreferences(): SharedPreferences

}
