package com.baiyu.androidx.basicmodule.network


sealed class NetResponse<out T> {

    data class Success<T>(val value: BaseResponse<T>) : NetResponse<T>() {
        val data = value.getResponseData()
        val code = value.getResponseCode()
        override fun toString() = "[MusicResponse.Success](data=$value)"
    }

    data class Failure<T>(val response: BaseResponse<T>) : NetResponse<T>() {
        private val errorCode = response.getResponseCode()
        private val errorMsg = response.getResponseMsg()
        override fun toString(): String =
            "[MusicResponse.Failure] (errorCode=$errorCode errorMsg=$errorMsg)"
    }

    data class Exception<T>(val exception: ApiException) : NetResponse<T>() {
        private val errorMsg: String? = exception.errorMessage
        private val errorCode = exception.errorCode
        override fun toString(): String =
            "[MusicResponse.Exception](errorCode=$errorCode errorMsg=$errorMsg)"
    }

}

