package com.baiyu.androidx.basicframework.repository

import com.baiyu.androidx.basicframework.http.RemoteService
import com.baiyu.androidx.basicmodule.base.relateViewCommon
import com.baiyu.androidx.basicmodule.livedata.BooleanLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author Baiyu
 * @date :2020/9/13 1:35 PM September
 * @version: 1.0
 */
class AppRepository(private val remoteService: RemoteService) : Repository {
    suspend fun getBannerList() = flow {
        isLoading.postValue(true)
        relateViewCommon { remoteService.getBanner() }
            .collect {
                emit(it)
                isLoading.postValue(false)
            }
    }.flowOn(Dispatchers.IO)

    override var isLoading: BooleanLiveData = BooleanLiveData()
}