package com.ivantrogrlic.leaguestats.main.summoner.ranks

import android.content.Context
import com.ivantrogrlic.leaguestats.dagger.ApplicationContext
import com.ivantrogrlic.leaguestats.dagger.PerFragment
import com.ivantrogrlic.leaguestats.web.RiotWebService
import com.ivantrogrlic.leaguestats.web.RiotWebServiceModule
import dagger.Module
import dagger.Provides

@Module(includes = [RiotWebServiceModule::class])
class RanksModule {

    @Provides
    @PerFragment
    internal fun ranksPresenter(@ApplicationContext context: Context, riotWebService: RiotWebService): RanksPresenter {
        return RanksPresenter(context, riotWebService)
    }

    @Provides
    @PerFragment
    internal fun provideranksFragmentView(ranksFragment: RanksFragment): RanksView {
        return ranksFragment
    }

}
