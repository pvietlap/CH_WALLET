package com.bautoidem.chwallet.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class DataBoundViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: T) {
        binding.executePendingBindings()
    }
}