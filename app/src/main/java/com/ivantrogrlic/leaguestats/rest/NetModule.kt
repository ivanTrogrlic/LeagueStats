package com.ivantrogrlic.leaguestats.rest

import com.ivantrogrlic.leaguestats.dagger.PerServer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

@Module
class NetModule(val baseUrl: String) {
  
  @Provides
  fun okHttpClient(): OkHttpClient =
      OkHttpClient.Builder()
          .connectTimeout(100, TimeUnit.SECONDS)
          .readTimeout(100, TimeUnit.SECONDS)
          .build()
  
  @PerServer
  @Provides
  fun retrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
  }
  
}