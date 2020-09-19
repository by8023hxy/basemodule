@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.base

import com.baiyu.androidx.basicmodule.ext.logD
import com.baiyu.androidx.basicmodule.ext.logE
import com.baiyu.androidx.basicmodule.network.*
import com.baiyu.androidx.basicmodule.util.ExceptionUtil
import com.baiyu.androidx.basicmodule.util.MMKVUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

private fun isLogin(): Boolean {
    return MMKVUtil.kvUtil.loginState
}

/**
 * 失败需要和UI关联(共同处理)
 */
suspend fun <T> relateViewCommon(request: suspend () -> BaseResponse<T>): Flow<T> {
    return flow {
        "flowUI onThread=${Thread.currentThread().name}".logD("Basic-->NetWorkThread")
        if (isLogin()) {
            //判断请求时，如果已登录的情况Token过期 要重新登录
            throw ApiException("Token过期，请重新登录", -100)
        } else {
            executeResponse(request()).suspendOnSuccess {
                emit(data)
            }.onFailure {
                BaseApp.shareViewModel.requestFailed.postValue(true)
                "flowUI onFailure==${message()}".logE()
            }
        }

    }.catch {
        val apiException = ExceptionUtil.getApiException(it)
        "flowUI CATCH==${apiException.errorMessage + apiException.errorCode}".logE()
    }
}

/**
 * 失败不需要和UI关联
 */
suspend fun <T> flowNormal(request: suspend () -> BaseResponse<T>): Flow<T> {
    return flow {
        "flowNormal onThread=${Thread.currentThread().name}".logD("Basic-->NetWorkThread")
        executeResponse(request()).suspendOnSuccess {
            emit(data)
        }.onFailure {
            "flowNormal onFailure==${message()}".logE()
        }
    }.catch {
        val apiException = ExceptionUtil.getApiException(it)
        "flowNormal CATCH==${apiException.errorMessage + apiException.errorCode}".logE()
    }
}

/**
 * 需要单独处理和UI关联的失败的请求
 */
suspend fun <T> associatedView(
    request: suspend () -> BaseResponse<T>,
    onError: suspend (ApiException) -> Unit
): Flow<T> {
    return flow {
        "flowNormal onThread=${Thread.currentThread().name}".logD("Basic-->NetWorkThread")
        if (isLogin()) {
            //判断请求时，如果已登录的情况Token过期 要重新登录
            throw ApiException("Token过期，请重新登录", -100)
        } else {
            executeResponse(request()).suspendOnSuccess {
                emit(data)
            }.suspendOnFailure {
                onError(ApiException(response.getResponseMsg(), response.getResponseCode()))
                "flowNormal onFailure==${message()}".logE()
            }
        }
    }.catch {
        val apiException = ExceptionUtil.getApiException(it)
        "flowNormal CATCH==${apiException.errorMessage + apiException.errorCode}".logE()
    }
}