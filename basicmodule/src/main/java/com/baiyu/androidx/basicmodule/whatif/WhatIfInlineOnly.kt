package com.baiyu.androidx.basicmodule.whatif

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@DslMarker
@Retention(AnnotationRetention.BINARY)
annotation class WhatIfInlineOnly
