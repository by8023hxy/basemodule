package com.baiyu.androidx.basicmodule.interceptor


import com.baiyu.androidx.basicmodule.App
import com.baiyu.androidx.basicmodule.ext.isNetworkAvailable
import com.baiyu.androidx.basicmodule.ext.logD
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 描述　: 缓存拦截器
 */
class CacheInterceptor(var day: Int = 30) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!App.instance.isNetworkAvailable()) {
            val maxStale = 60 * 60 * 24 * day
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            "cache request===${request.url()}".logD("CacheInterceptor")
        }
        return chain.proceed(request)
    }
}