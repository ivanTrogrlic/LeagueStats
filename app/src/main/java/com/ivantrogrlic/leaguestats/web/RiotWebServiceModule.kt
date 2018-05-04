package com.ivantrogrlic.leaguestats.web

import android.content.SharedPreferences
import com.ivantrogrlic.leaguestats.model.ServiceProxy
import com.ivantrogrlic.leaguestats.util.Preferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RiotWebServiceModule {

    @Provides
    internal fun riotWebService(retrofit: Retrofit, sharedPreferences: SharedPreferences): RiotWebService {
        val server = sharedPreferences.getString(Preferences.SERVER_PROXY_KEY, Preferences.NO_SERVER_SELECTED)
        val serverHost = ServiceProxy.valueOf(server).serverHost()
        return retrofit.newBuilder().baseUrl(serverHost).build().create(RiotWebService::class.java)
    }

}
