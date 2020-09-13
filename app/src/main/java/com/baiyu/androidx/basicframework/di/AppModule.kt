package com.baiyu.androidx.basicframework.di

import com.baiyu.androidx.basicframework.http.RemoteService
import com.baiyu.androidx.basicframework.repository.AppRepository
import com.baiyu.androidx.basicframework.ui.main.MainViewModel
import com.baiyu.androidx.basicmodule.BaseConstant
import com.baiyu.androidx.basicmodule.base.BaseApp
import com.baiyu.androidx.basicmodule.interceptor.CacheInterceptor
import com.baiyu.androidx.basicmodule.interceptor.CacheNetworkInterceptor
import com.baiyu.androidx.basicmodule.interceptor.logger.LogInterceptor
import com.baiyu.androidx.basicmodule.network.CoroutineCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author Baiyu
 * @date :2020/9/13 1:05 PM September
 * @version: 1.0
 */

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { AppRepository(get()) }
}

val httpModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .cache(Cache(File(BaseApp.CONTEXT.cacheDir, "by_cache"), 1024 * 1024 * 256L))
            .addInterceptor(CacheInterceptor())
            .addInterceptor(LogInterceptor())    // 日志拦截器
            .addNetworkInterceptor(CacheNetworkInterceptor())
            .connectTimeout(BaseConstant.HTTP_WRITE_TIME, TimeUnit.SECONDS)
            .readTimeout(BaseConstant.HTTP_READ_TIME, TimeUnit.SECONDS)
            .writeTimeout(BaseConstant.HTTP_CONNECT_TIME, TimeUnit.SECONDS)
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(RemoteService.BASE_URL)
            .build()
    }
    single<RemoteService> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(RemoteService.BASE_URL)
            .build().create(RemoteService::class.java)
    }
}
val applicationModules = listOf(
    viewModelModule,
    repositoryModule, httpModule
)
