package com.baiyu.androidx.basicmodule.databind

import androidx.databinding.ObservableField

class StringObservableField(value: String = "") : ObservableField<String>(value) {
    override fun get(): String {
        return super.get()!!
    }
}