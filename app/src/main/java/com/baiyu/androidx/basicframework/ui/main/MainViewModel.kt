package com.baiyu.androidx.basicframework.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.baiyu.androidx.basicframework.bean.BannerInfo
import com.baiyu.androidx.basicframework.repository.AppRepository
import com.baiyu.androidx.basicmodule.base.LiveCoroutinesViewModel

/**
 * @author Baiyu
 * @date :2020/9/13 1:36 PM September
 * @version: 1.0
 */
class MainViewModel(private val repository: AppRepository) : LiveCoroutinesViewModel() {

    val banner: ObservableField<List<BannerInfo>> = ObservableField()

    private val _bannerList = MutableLiveData<Boolean>()

    val bannerList = _bannerList.switchMap {
        launchOnViewModelScope { repository.getBannerList() }
    }

    fun getBannerList() {
        _bannerList.postValue(true)
    }
}