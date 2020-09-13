package com.baiyu.androidx.basicmodule.network

/**
 * @ProjectName:AgentHmi
 * @Author:BaiYu
 * @Email:baiyu@autoai.com
 * @Time:2020/8/19 14:58
 * @description：
 */

fun <T> executeResponse(
        response: BaseResponse<T>
): MusicResponse<T> {
    return if (response.isSuccess()) {
        MusicResponse.Success(response)
    } else {
        MusicResponse.Failure(response)
    }
}


/**
 * A suspend scope function for handling success response [MusicResponse.Success] a unit
 * block of code within the context of the response.
 */
@SuspensionFunction
suspend fun <T> MusicResponse<T>.suspendOnSuccess(
        onResult: suspend MusicResponse.Success<T>.() -> Unit
): MusicResponse<T> {
    if (this is MusicResponse.Success) {
        onResult(this)
    }
    return this
}

/**
 * A suspend scope function for handling failure response [MusicResponse.Failure] a unit
 * block of code within the context of the response.
 */
@SuspensionFunction
suspend fun <T> MusicResponse<T>.suspendOnFailure(onResult: suspend MusicResponse.Failure<*>.() -> Unit): MusicResponse<T> {
    if (this is MusicResponse.Failure<*>) {
        onResult(this)
    }
    return this
}

/**
 * A scope function for handling failure response [MusicResponse.Failure] a unit
 * block of code within the context of the response.
 */
fun <T> MusicResponse<T>.onFailure(onResult: MusicResponse.Failure<*>.() -> Unit): MusicResponse<T> {
    if (this is MusicResponse.Failure<*>) {
        onResult(this)
    }
    return this
}

/**
 * A scope function for handling exception response [MusicResponse.Exception] a unit
 * block of code within the context of the response.
 */
fun <T> MusicResponse<T>.onException(onResult: MusicResponse.Exception<*>.() -> Unit): MusicResponse<T> {
    if (this is MusicResponse.Exception<*>) {
        onResult(this)
    }
    return this
}


/** A message from the [MusicResponse.Failure]. */
fun <T> MusicResponse.Failure<T>.message(): String = toString()

/** A message from the [MusicResponse.Exception]. */
fun <T> MusicResponse.Exception<T>.message(): String = toString()

