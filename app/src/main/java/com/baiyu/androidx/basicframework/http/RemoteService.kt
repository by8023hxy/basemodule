package com.baiyu.androidx.basicframework.http

import com.baiyu.androidx.basicframework.bean.BannerInfo
import retrofit2.http.GET

/**
 * @author Baiyu
 * @date :2020/9/13 1:11 PM September
 * @version: 1.0
 */
interface RemoteService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<BannerInfo>>
}