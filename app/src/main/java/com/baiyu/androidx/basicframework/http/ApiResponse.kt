package com.baiyu.androidx.basicframework.http

import com.baiyu.androidx.basicmodule.network.BaseResponse


data class ApiResponse<T>(var code: Int, var msg: String, var data: T) : BaseResponse<T>() {

    override fun isSuccess() = code == 0

    override fun getResponseCode() = code

    override fun getResponseData() = data

    override fun getResponseMsg() = msg

}