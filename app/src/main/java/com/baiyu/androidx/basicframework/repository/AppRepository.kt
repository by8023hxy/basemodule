package com.baiyu.androidx.basicframework.repository

import com.baiyu.androidx.basicframework.http.RemoteService
import com.baiyu.androidx.basicmodule.base.flowScope
import com.baiyu.androidx.basicmodule.base.liveDataScope

/**
 * @author Baiyu
 * @date :2020/9/13 1:35 PM September
 * @version: 1.0
 */
class AppRepository(private val remoteService: RemoteService) {

    suspend fun getBannerList() = liveDataScope { remoteService.getBanner() }

}