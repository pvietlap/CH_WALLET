package com.bautoidem.chwallet.ui.price_coin.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bautoidem.chwallet.base.OnClickItem
import com.bautoidem.chwallet.base.adapter.CoreRecyclerViewAdapter
import com.bautoidem.chwallet.base.adapter.DataBoundViewHolder
import com.bautoidem.chwallet.ui.price_coin.data.ItemPriceCoinSealed
import com.bautoidem.chwallet.ui.price_coin.data.TypePriceCoin
import com.bautoidem.chwallet.ui.price_coin.view_holder.ItemEmptyCoinViewHolder
import com.bautoidem.chwallet.ui.price_coin.view_holder.ItemPriceCoinViewHolder

class PriceCoinAdapter(context: Context?, var callback: OnClickItem) :
    CoreRecyclerViewAdapter<ItemPriceCoinSealed>(context = context, diffCallback = object :
        DiffUtil.ItemCallback<ItemPriceCoinSealed>() {
        override fun areItemsTheSame(
            oldItem: ItemPriceCoinSealed,
            newItem: ItemPriceCoinSealed
        ): Boolean {
            return oldItem.type != newItem.type
        }

        override fun areContentsTheSame(
            oldItem: ItemPriceCoinSealed,
            newItem: ItemPriceCoinSealed
        ): Boolean {
            return oldItem.type != newItem.type
        }

    }) {

    override fun getItemCount(): Int = currentList.size

    override fun getItemViewType(position: Int): Int = currentList[position].type

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBoundViewHolder<ItemPriceCoinSealed> {
        return when (viewType) {
            TypePriceCoin.ITEM_PRICE_COIN -> {
                ItemPriceCoinViewHolder(context,createBinding(parent, viewType))
            }
            TypePriceCoin.ITEM_EMPTY->{
                ItemEmptyCoinViewHolder(context,createBinding(parent,viewType))
            }
            else -> {
                ItemPriceCoinViewHolder(context,createBinding(parent, viewType))
            }
        }
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemPriceCoinSealed>, position: Int) {
        val child = currentList[position]
        when (holder) {
            is ItemPriceCoinViewHolder -> {
                val data= (child as ItemPriceCoinSealed.ItemPriceCoinDisplay).data
                holder.bindData(data,callback)
            }
        }
    }
}