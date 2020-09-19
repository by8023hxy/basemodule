package com.baiyu.androidx.basicframework.repository

import com.baiyu.androidx.basicmodule.livedata.BooleanLiveData

/**
 * @author Baiyu
 * @date :2020/9/13 3:29 PM September
 * @version: 1.0
 */
/** Repository is an interface for configuring base repository classes. */
interface Repository {

    // this override property is for saving network loading status.
    var isLoading: BooleanLiveData
}