package com.baiyu.androidx.basicframework.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.baiyu.androidx.basicframework.repository.AppRepository
import com.baiyu.androidx.basicmodule.base.LiveCoroutinesViewModel

/**
 * @author Baiyu
 * @date :2020/9/13 1:36 PM September
 * @version: 1.0
 */
class MainViewModel(private val repository: AppRepository) : LiveCoroutinesViewModel() {

    private val _bannerList = MutableLiveData<Boolean>()

    val bannerList = _bannerList.switchMap {
        launchOnViewModelScope { repository.getBannerList().asLiveData() }
    }

    fun getBannerList() {
        _bannerList.value = true
    }

    fun isLoading(): LiveData<Boolean> = repository.isLoading
}