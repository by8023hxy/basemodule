package com.baiyu.androidx.basicmodule.network


/**
 * @ProjectName:AgentHmi
 * @Author:BaiYu
 * @Email:baiyu@autoai.com
 * @Time:2020/8/19 13:23
 * @description：
 */
sealed class MusicResponse<out T> {

    data class Success<T>(val value: BaseResponse<T>) : MusicResponse<T>() {
        val data = value.getResponseData()
        val code = value.getResponseCode()
        override fun toString() = "[MusicResponse.Success](data=$value)"
    }

    data class Failure<T>(val response: BaseResponse<T>) : MusicResponse<T>() {
        private val errorCode = response.getResponseCode()
        private val errorMsg = response.getResponseMsg()
        override fun toString(): String = "[MusicResponse.Failure] (errorCode=$errorCode errorMsg=$errorMsg)"
    }

    data class Exception<T>(val exception: ApiException) : MusicResponse<T>() {
        private val errorMsg: String? = exception.errorMessage
        private val errorCode = exception.errorCode
        override fun toString(): String = "[MusicResponse.Exception](errorCode=$errorCode errorMsg=$errorMsg)"
    }

}

