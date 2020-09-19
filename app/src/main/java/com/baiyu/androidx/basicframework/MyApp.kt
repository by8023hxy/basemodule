package com.baiyu.androidx.basicframework

import com.baiyu.androidx.basicframework.di.applicationModules
import com.baiyu.androidx.basicmodule.ShareViewModel
import com.baiyu.androidx.basicmodule.base.BaseApp
import com.baiyu.androidx.basicmodule.ext.logD
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author Baiyu
 * @date :2020/9/13 1:07 PM September
 * @version: 1.0
 */
class MyApp : BaseApp() {
    companion object {
        @JvmStatic
        lateinit var CONTEXT: BaseApp

        @JvmStatic
        val shareViewModel: ShareViewModel by lazy { ShareViewModel() }
    }

    override fun onCreate() {
        super.onCreate()
        "MyApp onCreate!!!".logD()
        CONTEXT = this
        MMKV.initialize(this)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            androidFileProperties()
            modules(applicationModules)
        }
    }
}