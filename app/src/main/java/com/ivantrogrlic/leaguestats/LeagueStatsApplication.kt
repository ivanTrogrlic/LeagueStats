package com.ivantrogrlic.leaguestats

import android.app.Application
import com.ivantrogrlic.leaguestats.dagger.AppComponent
import com.ivantrogrlic.leaguestats.dagger.DaggerAppComponent
import com.ivantrogrlic.leaguestats.web.NetComponent
import com.ivantrogrlic.leaguestats.web.NetModule

/** Created by ivanTrogrlic on 12/07/2017. */
class LeagueStatsApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        var netComponent: NetComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
        appComponent.inject(this)
    }

    fun component() = appComponent
    fun netComponent() = netComponent

    fun createNetComponent(baseUrl: String): NetComponent {
        netComponent = component()
                .netComponentBuilder()
                .netModule(NetModule(baseUrl))
                .build()
        return netComponent!!
    }

    fun destroyNetComponent() {
        netComponent = null
    }

}
