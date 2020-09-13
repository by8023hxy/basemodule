@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.whatif

/**
 * @ProjectName:AgentHmi
 * @Author:BaiYu
 * @Email:baiyu@autoai.com
 * @Time:2020/8/20 15:15
 * @description：
 */

/**
 * WhatIf is kotlin extensions for expressing a single if-else statement, nullable and boolean.
 */

/** An expression for invoking [whatIf] when the [given] boolean is true. */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
        given: (T) -> Boolean?,
        whatIf: () -> Unit
) {

    if (given(this) == true) {
        whatIf()
    }
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
        given: (T) -> Boolean?,
        whatIf: () -> Unit,
        whatIfNot: () -> Unit
) {

    if (given(this) == true) {
        whatIf()
    } else {
        whatIfNot()
    }
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
        given: Boolean?,
        whatIf: T.() -> Unit
): T {

    if (given == true) {
        this.apply { whatIf() }
    }
    return this
}

/**
 * An expression for invoking [whatIf] when the [given] boolean is true.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
        given: Boolean?,
        whatIf: T.() -> Unit,
        whatIfNot: T.() -> Unit
): T {

    if (given == true) {
        this.apply { whatIf() }
    } else {
        this.apply { whatIfNot() }
    }
    return this
}

/**
 * An expression for invoking [whatIf] when the [given] lambda's return value is true.
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
        given: () -> Boolean?,
        whatIfDo: T.() -> Unit
): T {

    return this.whatIf(
            given = given,
            whatIfDo = { whatIfDo() },
            whatIfNot = { }
    )
}

/**
 * An expression for invoking [whatIf] when the [given] lambda's return value is true.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 * So it is useful when using with a chaining function like builder pattern or [apply] expression in kotlin.
 */
@WhatIfInlineOnly
inline fun <T> T.whatIf(
        given: () -> Boolean?,
        whatIfDo: T.() -> Unit,
        whatIfNot: T.() -> Unit
): T {

    if (given() == true) {
        this.apply { whatIfDo() }
    } else {
        this.apply { whatIfNot() }
    }
    return this
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean value is false, the result value is the [default].
 * It is useful when the receiver [T] and the result [R] should be different.
 */
@WhatIfInlineOnly
inline fun <T, R> T.whatIfLet(
        given: Boolean?,
        default: R,
        whatIf: (T) -> R
): R {

    return this.whatIfLet(
            given = given,
            whatIf = { whatIf(it) },
            whatIfNot = { default }
    )
}

/**
 * An expression for invoking [whatIf] when the [given] boolean value.
 * If the [given] boolean is false, [whatIfNotNull] will be invoked instead of the [whatIf].
 * It is useful when the receiver [T] and the result [R] should be different.
 */
@WhatIfInlineOnly
inline fun <T, R> T.whatIfLet(
        given: Boolean?,
        whatIf: (T) -> R,
        whatIfNot: (T) -> R
): R {

    if (given == true) {
        return whatIf(this)
    }
    return whatIfNot(this)
}

/** An expression for invoking [whatIf] when the [T] target object is not null. */
@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
        whatIf: (T) -> Unit
) {

    this.whatIfNotNull(
            whatIf = { whatIf(it) },
            whatIfNot = { }
    )
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * If the [T] target is null, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun <T> T?.whatIfNotNull(
        whatIf: (T) -> Unit,
        whatIfNot: (T?) -> Unit
) {

    if (this != null) {
        whatIf(this)
    } else {
        whatIfNot(this)
    }
}

/**
 * An expression for invoking [whatIf] when the target object is not null.
 * If the target is not null, the receiver will get a casted [R] type.
 */
@WhatIfInlineOnly
inline fun <reified R> Any?.whatIfNotNullAs(
        whatIf: (R) -> Unit
) {

    if (this != null) {
        whatIf(this as R)
    }
}

/**
 * An expression for invoking [whatIf] when the target object is not null.
 * If the target is not null, the receiver will get a casted [R] type.
 * If the target is null, [whatIfNot] will be invoked instead of the [whatIf] without casting.
 */
@WhatIfInlineOnly
inline fun <reified R> Any?.whatIfNotNullAs(
        whatIf: (R) -> Unit,
        whatIfNot: () -> Unit
) {

    if (this != null) {
        whatIf(this as R)
    } else {
        whatIfNot()
    }
}

/**
 * An expression for invoking [whatIf] when the [T] target object is not null.
 * If the [T] target is null, [whatIfNot] will be invoked instead of the [whatIf].
 * It is useful when the receiver [T] and the result [R] should be different.
 */
@WhatIfInlineOnly
inline fun <T, R> T?.whatIfNotNullWith(
        whatIf: (T) -> R,
        whatIfNot: (T?) -> R
): R {

    if (this != null) {
        return whatIf(this)
    }
    return whatIfNot(this)
}

/** An expression for invoking [whatIf] when the target object is not null and true. */
@WhatIfInlineOnly
inline fun Boolean?.whatIf(
        whatIf: () -> Unit
) {

    this.whatIf(
            whatIf = { whatIf() },
            whatIfNot = { }
    )
}

/**
 * An expression for invoking [whatIf] when the target object is not null and true.
 * If the target is null or false, [whatIfNot] will be invoked instead of the [whatIf].
 */
@WhatIfInlineOnly
inline fun Boolean?.whatIf(
        whatIf: () -> Unit,
        whatIfNot: () -> Unit
) {

    if (this == true) {
        whatIf()
    } else {
        whatIfNot()
    }
}

/** An expression for invoking [whatIf] when the target object is not null and false. */
@WhatIfInlineOnly
inline fun Boolean?.whatIfElse(
        whatIf: () -> Unit
) {

    if (this == false) {
        whatIf()
    }
}

/** An expression for invoking [whatIf] when the target Boolean is true and the [predicate] is also true. */
@WhatIfInlineOnly
inline fun Boolean?.whatIfAnd(
        predicate: Boolean?,
        whatIf: () -> Unit
) {

    if (this == true && predicate == true) {
        whatIf()
    }
}

/** An expression for invoking [whatIf] when the target Boolean is true or the [predicate] is true. */
@WhatIfInlineOnly
inline fun Boolean?.whatIfOr(
        predicate: Boolean?,
        whatIf: () -> Unit
) {

    if (this == true || predicate == true) {
        whatIf()
    }
}