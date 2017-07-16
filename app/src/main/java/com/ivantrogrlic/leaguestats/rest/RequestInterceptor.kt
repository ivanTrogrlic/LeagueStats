package com.ivantrogrlic.leaguestats.rest

import com.ivantrogrlic.leaguestats.BuildConfig
import com.ivantrogrlic.leaguestats.dagger.PerServer
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * Created by ivanTrogrlic on 16/07/2017.
 */

@PerServer
class RequestInterceptor @Inject constructor() : Interceptor {
  
  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()
    val originalHttpUrl = original.url()
    
    val url = originalHttpUrl.newBuilder()
        .addQueryParameter("api_key", BuildConfig.RIOT_API_KEY)
        .build()
    
    val request = original.newBuilder().url(url).build()
    return chain.proceed(request)
  }
  
}
