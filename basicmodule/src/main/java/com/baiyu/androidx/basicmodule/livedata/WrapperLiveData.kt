@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.baiyu.androidx.basicmodule.event.Event


//为 LiveData<Event<T>>提供类型别名，使用 EventLiveData<T> 即可
typealias EventMutableLiveData<T> = MutableLiveData<Event<T>>

typealias EventLiveData<T> = LiveData<Event<T>>

/**
 * 事件可被多个观察者消费，且每个观察者仅能消费一次
 */
@MainThread
inline fun <T> EventLiveData<T>.observeSingleEvent(
    owner: LifecycleOwner,
    viewModelStore: ViewModelStore,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        //数据没有被使用过则发送给调用者，否则不处理
        t.getContentIfNotHandled(viewModelStore)?.let { data ->
            onChanged.invoke(data)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

/**
 * 事件只能被唯一观察者消费
 */
@MainThread
inline fun <T> EventMutableLiveData<T>.observeEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        //数据没有被使用过则发送给调用者，否则不处理
        t.getContentIfNotHandled()?.let { data ->
            onChanged.invoke(data)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}


@MainThread
inline fun <T> EventLiveData<T>.observeEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        onChanged.invoke(t.peekContent())
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

fun <T> EventMutableLiveData<T>.postEventValue(value: T) {
    postValue(Event(value))
}

fun <T> EventMutableLiveData<T>.setEventValue(value: T) {
    setValue(Event(value))
}