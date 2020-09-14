
package com.baiyu.androidx.basicframework.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baiyu.androidx.basicframework.adapter.DemoAdapter
import com.baiyu.androidx.basicframework.bean.BannerInfo
import com.baiyu.androidx.basicmodule.view.decoration.SpaceItemDecoration
import com.baiyu.androidx.basicmodule.whatif.whatIfNotNullOrEmpty


@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterBannerList")
fun bindAdapterList(view: RecyclerView, bannerList: List<BannerInfo>?) {
    bannerList.whatIfNotNullOrEmpty {
        (view.adapter as? DemoAdapter)?.addBannerList(it)
    }
    view.addItemDecoration(SpaceItemDecoration(0,0,10,0))
}
