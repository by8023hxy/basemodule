package com.baiyu.androidx.basicmodule.livedata

import androidx.lifecycle.MutableLiveData

class ShortLiveData : MutableLiveData<Short>() {
    override fun getValue(): Short {
        return super.getValue() ?: 0
    }
}