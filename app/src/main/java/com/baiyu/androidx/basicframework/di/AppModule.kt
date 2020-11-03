package com.baiyu.androidx.basicframework.di

import com.baiyu.androidx.basicframework.MyApp
import com.baiyu.androidx.basicframework.http.RemoteService
import com.baiyu.androidx.basicframework.repository.AppRepository
import com.baiyu.androidx.basicframework.ui.main.MainViewModel
import com.baiyu.androidx.basicframework.util.MMKVUtil
import com.baiyu.androidx.basicmodule.BaseConstant
import com.baiyu.androidx.basicmodule.interceptor.CacheInterceptor
import com.baiyu.androidx.basicmodule.interceptor.CacheNetworkInterceptor
import com.baiyu.androidx.basicmodule.interceptor.logger.LogInterceptor
import com.baiyu.androidx.basicmodule.network.CoroutineCallAdapterFactory
import com.google.gson.GsonBuilder
import com.tencent.mmkv.MMKV
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author Baiyu
 * @date :2020/9/13 1:05 PM September
 * @version: 1.0
 */

val baseModule = module {

    single {
        MMKVUtil(get())
    }
    single<MMKV> {
        MMKV.mmkvWithID(
            BaseConstant.MMKV_ID,
            MMKV.MULTI_PROCESS_MODE,
            BaseConstant.MMKV_CRYPT_KEY
        )
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { AppRepository(get()) }
}

val httpModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .cache(Cache(File(MyApp.CONTEXT.cacheDir, "by_cache"), 1024 * 1024 * 256L))
            .addInterceptor(CacheInterceptor())
            .addInterceptor(LogInterceptor())    // 日志拦截器
            .addNetworkInterceptor(CacheNetworkInterceptor())
            .connectTimeout(BaseConstant.HTTP_CONNECT_TIME, TimeUnit.SECONDS)
            .readTimeout(BaseConstant.HTTP_READ_TIME, TimeUnit.SECONDS)
            .writeTimeout(BaseConstant.HTTP_WRITE_TIME, TimeUnit.SECONDS)
            .build()
    }
    single<RemoteService> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(RemoteService.BASE_URL)
            .build().create(RemoteService::class.java)
    }
}

val applicationModules = listOf(
    baseModule,
    viewModelModule,
    repositoryModule, httpModule
)
