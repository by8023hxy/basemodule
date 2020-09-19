package com.baiyu.androidx.basicmodule.network

/**
 * @ProjectName:AgentHmi
 * @Author:BaiYu
 * @Email:baiyu@autoai.com
 * @Time:2020/7/15 10:59
 * @description：务器返回数据的基类
 */
abstract class BaseResponse<T> {

    //抽象方法，用户的基类继承该类时，需要重写该方法
    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}