package com.ivantrogrlic.leaguestats

import com.ivantrogrlic.leaguestats.dagger.DaggerAppComponent
import dagger.android.DaggerApplication

/** Created by ivanTrogrlic on 12/07/2017. */
class LeagueStatsApplication : DaggerApplication() {

    override fun applicationInjector() =
            DaggerAppComponent
                    .builder()
                    .application(this)
                    .build()

}
