package com.baiyu.androidx.basicmodule.network

/**
 * 用来封装业务错误信息
 *
 * @author Baiyu
 * @date 2020-05-09
 */
class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()