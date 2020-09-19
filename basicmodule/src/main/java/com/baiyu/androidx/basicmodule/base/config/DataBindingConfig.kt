package com.baiyu.androidx.basicmodule.base.config

import android.util.SparseArray
import androidx.lifecycle.ViewModel
import com.baiyu.androidx.basicmodule.base.BaseViewModel

/**
 * @author Baiyu
 * @date :2020/9/19 10:19 AM September
 * @version: 1.0 dataBinding配置类
 * 感谢:KunMinX 详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
 */
class DataBindingConfig(
    private var mLayoutId: Int,
    private var mVariableId: Int,
    private var mViewModel: BaseViewModel
) {

    private val bindingParams: SparseArray<Any> = SparseArray()

    fun getLayoutId(): Int {
        return mLayoutId
    }

    fun getVariableId(): Int {
        return mVariableId
    }

    fun getViewModel(): BaseViewModel {
        return mViewModel
    }

    fun getBindingParams(): SparseArray<Any>? {
        return bindingParams
    }

    fun addBindingParam(variableId: Int, any: Any?): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, any)
        }
        return this
    }

}