/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
