package com.ivantrogrlic.leaguestats.main.summoner.games

import android.content.Context
import com.ivantrogrlic.leaguestats.dagger.ApplicationContext
import com.ivantrogrlic.leaguestats.dagger.PerFragment
import com.ivantrogrlic.leaguestats.web.RiotWebService
import com.ivantrogrlic.leaguestats.web.RiotWebServiceModule
import dagger.Module
import dagger.Provides

@Module(includes = [RiotWebServiceModule::class])
class GamesModule {

    @Provides
    @PerFragment
    internal fun gamesPresenter(@ApplicationContext context: Context, riotWebService: RiotWebService): GamesPresenter {
        return GamesPresenter(context, riotWebService)
    }

    @Provides
    @PerFragment
    internal fun provideGamesFragmentView(ranksFragment: GamesFragment): GamesView {
        return ranksFragment
    }

}
