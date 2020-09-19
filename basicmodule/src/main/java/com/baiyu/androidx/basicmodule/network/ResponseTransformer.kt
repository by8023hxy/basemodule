package com.baiyu.androidx.basicmodule.network


fun <T> executeResponse(
    response: BaseResponse<T>
): NetResponse<T> {
    return if (response.isSuccess()) {
        NetResponse.Success(response)
    } else {
        NetResponse.Failure(response)
    }
}


/**
 * A suspend scope function for handling success response [NetResponse.Success] a unit
 * block of code within the context of the response.
 */
@SuspensionFunction
suspend fun <T> NetResponse<T>.suspendOnSuccess(
    onResult: suspend NetResponse.Success<T>.() -> Unit
): NetResponse<T> {
    if (this is NetResponse.Success) {
        onResult(this)
    }
    return this
}

/**
 * A suspend scope function for handling failure response [NetResponse.Failure] a unit
 * block of code within the context of the response.
 */
@SuspensionFunction
suspend fun <T> NetResponse<T>.suspendOnFailure(onResult: suspend NetResponse.Failure<*>.() -> Unit): NetResponse<T> {
    if (this is NetResponse.Failure<*>) {
        onResult(this)
    }
    return this
}

/**
 * A scope function for handling failure response [NetResponse.Failure] a unit
 * block of code within the context of the response.
 */
fun <T> NetResponse<T>.onFailure(onResult: NetResponse.Failure<*>.() -> Unit): NetResponse<T> {
    if (this is NetResponse.Failure<*>) {
        onResult(this)
    }
    return this
}

/**
 * A scope function for handling exception response [NetResponse.Exception] a unit
 * block of code within the context of the response.
 */
fun <T> NetResponse<T>.onException(onResult: NetResponse.Exception<*>.() -> Unit): NetResponse<T> {
    if (this is NetResponse.Exception<*>) {
        onResult(this)
    }
    return this
}


/** A message from the [NetResponse.Failure]. */
fun <T> NetResponse.Failure<T>.message(): String = toString()

/** A message from the [NetResponse.Exception]. */
fun <T> NetResponse.Exception<T>.message(): String = toString()

