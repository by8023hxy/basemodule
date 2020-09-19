@file:Suppress("unused")
package com.baiyu.androidx.basicmodule.base

import com.baiyu.androidx.basicmodule.network.ApiException
import com.baiyu.androidx.basicmodule.network.BaseResponse
import com.baiyu.androidx.basicmodule.util.MMKVUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @description：Flow Repo
 */
fun <T> flowRequest(request: suspend () -> BaseResponse<T>): Flow<T> {
    return flow {

        if (MMKVUtil.kvUtil.loginState) {
            //判断请求时，如果已登录的情况Token过期 要重新登录
            throw ApiException("Token过期，请重新登录", -100)
        } else {
            val response = request()
            when (response.getResponseCode()) {
                0 -> {
                    emit(response.getResponseData())
                }

                else -> {
                    throw ApiException(response.getResponseMsg(), response.getResponseCode())
                }

            }
        }
    }.flowOn(Dispatchers.IO)
}

fun <T> flowRequestNoCheck(request: suspend () -> T): Flow<T> {
    return flow {
        emit(request())
    }.flowOn(Dispatchers.IO)
}