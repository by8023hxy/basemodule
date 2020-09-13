package com.baiyu.androidx.basicmodule.event

import androidx.lifecycle.ViewModelStore

/**
 * 使用 包装类 解决 LiveData 粘性事件的问题
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    var map = HashMap<ViewModelStore, Boolean>()

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(viewModelStore: ViewModelStore): T? {
        return if (map.containsKey(viewModelStore)) {
            null
        } else {
            map[viewModelStore] = true
            content
        }
        /* return if (hasBeenHandled) {
             null
         } else {
             hasBeenHandled = true
             content
         }*/
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}