@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.whatif

/** An expression for invoking [whatIf] when the [List] is not null and not empty. */
@WhatIfInlineOnly
inline fun <T> List<T>?.whatIfNotNullOrEmpty(
    whatIf: (List<T>) -> Unit
) {

    this.whatIfNotNullOrEmpty(
        whatIf = { whatIf(it) },
        whatIfNot = { }
    )
}

/**
 * An expression for invoking [whatIf] when the [List] is not null and not empty.
 * If the array is null or empty, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun <T> List<T>?.whatIfNotNullOrEmpty(
    whatIf: (List<T>) -> Unit,
    whatIfNot: () -> Unit
) {

    if (!this.isNullOrEmpty()) {
        whatIf(this)
    } else {
        whatIfNot()
    }
}

/** An expression for invoking [whatIf] when the [Set] is not null and not empty. */
@WhatIfInlineOnly
inline fun <T> Set<T>?.whatIfNotNullOrEmpty(
    whatIf: (Set<T>) -> Unit
) {

    this.whatIfNotNullOrEmpty(
        whatIf = { whatIf(it) },
        whatIfNot = { }
    )
}

/**
 * An expression for invoking [whatIf] when the [Set] is not null and not empty.
 * If the array is null or empty, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun <T> Set<T>?.whatIfNotNullOrEmpty(
    whatIf: (Set<T>) -> Unit,
    whatIfNot: () -> Unit
) {

    if (!this.isNullOrEmpty()) {
        whatIf(this)
    } else {
        whatIfNot()
    }
}

/** An expression for invoking [whatIf] when the [Map] is not null and not empty. */
@WhatIfInlineOnly
inline fun <T, R> Map<T, R>?.whatIfNotNullOrEmpty(
    whatIf: (Map<T, R>) -> Unit
) {

    this.whatIfNotNullOrEmpty(
        whatIf = { whatIf(it) },
        whatIfNot = { }
    )
}

/**
 * An expression for invoking [whatIf] when the [Map] is not null and not empty.
 * If the array is null or empty, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun <T, R> Map<T, R>?.whatIfNotNullOrEmpty(
    whatIf: (Map<T, R>) -> Unit,
    whatIfNot: () -> Unit
) {

    if (!this.isNullOrEmpty()) {
        whatIf(this)
    } else {
        whatIfNot()
    }
}