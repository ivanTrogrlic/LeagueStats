package com.ivantrogrlic.leaguestats.rest

import com.ivantrogrlic.leaguestats.dagger.PerServer
import dagger.Subcomponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

@PerServer
@Subcomponent(modules = arrayOf(NetModule::class))
interface NetComponent {
  
  fun retrofit(): Retrofit
  fun okHttp(): OkHttpClient
  fun requestInterceptor(): Interceptor
  
  @Subcomponent.Builder
  interface Builder {
    fun netModule(netModule: NetModule): Builder
    fun build(): NetComponent
  }
  
}
