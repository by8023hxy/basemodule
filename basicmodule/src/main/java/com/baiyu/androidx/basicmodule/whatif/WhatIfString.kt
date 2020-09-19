@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.whatif


/** An expression for invoking [whatIf] when the [String] is not null and not empty. */
@WhatIfInlineOnly
inline fun String?.whatIfNotNullOrEmpty(
    whatIf: (String) -> Unit
) {

    this.whatIfNotNullOrEmpty(
        whatIf = { whatIf(it) },
        whatIfNot = { }
    )
}

/**
 * An expression for invoking [whatIf] when the [String] is not null and not empty.
 * If the array is null or empty, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun String?.whatIfNotNullOrEmpty(
    whatIf: (String) -> Unit,
    whatIfNot: () -> Unit
) {

    if (!this.isNullOrEmpty()) {
        whatIf(this)
    } else {
        whatIfNot()
    }
}