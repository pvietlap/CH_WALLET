package com.bautoidem.chwallet.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import java.util.concurrent.Executors

abstract class CoreRecyclerViewAdapter<T>(var context: Context?, diffCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, DataBoundViewHolder<T>>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    fun <V : ViewDataBinding> createBinding(parent: ViewGroup, layoutID: Int): V {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutID,
            parent,
            false,
        )
    }

}