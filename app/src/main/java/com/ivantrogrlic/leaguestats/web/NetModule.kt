package com.ivantrogrlic.leaguestats.web

import android.content.SharedPreferences
import com.ivantrogrlic.leaguestats.model.ServiceProxy
import com.ivantrogrlic.leaguestats.util.Preferences.Companion.NO_SERVER_SELECTED
import com.ivantrogrlic.leaguestats.util.Preferences.Companion.SERVER_PROXY_KEY
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

@Module
class NetModule {

    @Provides
    @Singleton
    fun requestInterceptor(interceptor: RequestInterceptor): Interceptor = interceptor

    @Provides
    @Singleton
    fun okHttpClient(requestInterceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(logging)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun baseUrl(sharedPreferences: SharedPreferences): String {
        val server = sharedPreferences.getString(SERVER_PROXY_KEY, NO_SERVER_SELECTED)
        return ServiceProxy.valueOf(server).serverHost()
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, @Named("BASE_URL") baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    @Named("DEFAULT_RIOT_WEB_SERVICE")
    fun riotWebService(retrofit: Retrofit): RiotWebService =
            retrofit.create(RiotWebService::class.java)
}
