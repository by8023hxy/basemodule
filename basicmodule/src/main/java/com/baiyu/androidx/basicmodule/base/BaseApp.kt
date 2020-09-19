package com.baiyu.androidx.basicmodule.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.baiyu.androidx.basicmodule.ShareViewModel
import com.baiyu.androidx.basicmodule.di.baseModule
import com.baiyu.androidx.basicmodule.di.netWorkModule
import com.baiyu.androidx.basicmodule.ext.logD
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * @author Baiyu
 * @date :2020/9/13 12:55 PM September
 * @version: 1.0
 */
open class BaseApp : Application(), ViewModelStoreOwner {
    companion object {
        @JvmStatic
        lateinit var CONTEXT: BaseApp
        @JvmStatic
        val shareViewModel: ShareViewModel by lazy { ShareViewModel() }
    }

    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        "BaseApp  onCreate!!!!".logD()
        mAppViewModelStore = ViewModelStore()
        CONTEXT = this
        MMKV.initialize(this)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApp)
            androidFileProperties()
            modules(baseModule)
        }
        ignoreSSLHandshake()
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

    /**
     * 忽略SSL证书
     */
    @SuppressLint("TrustAllX509TrustManager")
    private fun ignoreSSLHandshake() {
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                    return arrayOfNulls(0)
                }

                override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
                override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
            })
            val sc = SSLContext.getInstance("TLS")
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
            HttpsURLConnection.setDefaultHostnameVerifier { _, _ -> true }
        } catch (e: Exception) {
        }
    }
}