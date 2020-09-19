package com.baiyu.androidx.basicmodule.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CacheNetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().removeHeader("Pragma")
            .addHeader("Cache-Control", "max-age=60").build()
        return chain.proceed(request)
    }
}