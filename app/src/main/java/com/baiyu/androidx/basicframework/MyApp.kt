package com.baiyu.androidx.basicframework

import com.baiyu.androidx.basicframework.di.applicationModules
import com.baiyu.androidx.basicmodule.base.BaseApp
import com.baiyu.androidx.basicmodule.ext.logD
import org.koin.core.context.loadKoinModules

/**
 * @author Baiyu
 * @date :2020/9/13 1:07 PM September
 * @version: 1.0
 */
class MyApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        "MyApp onCreate!!!".logD()
        loadKoinModules(applicationModules)
    }
}