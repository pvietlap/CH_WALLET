package com.bautoidem.chwallet.ui.price_coin.view_holder

import android.content.Context
import com.bautoidem.chwallet.base.adapter.DataBoundViewHolder
import com.bautoidem.chwallet.databinding.ItemCoinBinding
import com.bautoidem.chwallet.databinding.ItemEmptyDataBinding
import com.bautoidem.chwallet.ui.price_coin.data.ItemPriceCoinSealed

class ItemEmptyCoinViewHolder(var context: Context?, private val binding: ItemEmptyDataBinding) :
    DataBoundViewHolder<ItemPriceCoinSealed>(binding) {
}