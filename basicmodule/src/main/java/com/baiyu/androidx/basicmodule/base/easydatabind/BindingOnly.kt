package com.baiyu.androidx.basicmodule.base.easydatabind

/**
 * Specifies that this annotation should be used to mark binding properties and functions.
 */
@Target(
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER
)
@DslMarker
@Retention(AnnotationRetention.BINARY)
internal annotation class BindingOnly
