package com.baiyu.androidx.basicframework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.baiyu.androidx.basicframework.R
import com.baiyu.androidx.basicframework.bean.BannerInfo
import com.baiyu.androidx.basicframework.databinding.ItemDemoBinding
import com.baiyu.androidx.basicmodule.base.BaseApp
import com.baiyu.androidx.basicmodule.ext.toast


class DemoAdapter : RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {

    private val items: MutableList<BannerInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemDemoBinding>(inflater, R.layout.item_demo, parent, false)
        return DemoViewHolder(binding)
    }

    fun addBannerList(bannerList: List<BannerInfo>) {
        items.addAll(bannerList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            banner = item
            executePendingBindings()
            root.setOnClickListener {
               it.context.toast(item.title)
            }
        }
    }

    override fun getItemCount() = items.size

    class DemoViewHolder(val binding: ItemDemoBinding) :
        RecyclerView.ViewHolder(binding.root)
}
