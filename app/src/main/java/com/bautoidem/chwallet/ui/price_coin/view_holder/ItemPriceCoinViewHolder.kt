package com.bautoidem.chwallet.ui.price_coin.view_holder

import android.content.Context
import com.bautoidem.chwallet.base.OnClickItem
import com.bautoidem.chwallet.base.adapter.DataBoundViewHolder
import com.bautoidem.chwallet.data.PriceCoin
import com.bautoidem.chwallet.databinding.ItemCoinBinding
import com.bautoidem.chwallet.ui.price_coin.data.ItemPriceCoinSealed
import com.bautoidem.chwallet.utils.ImageUtils

class ItemPriceCoinViewHolder(var context: Context?,private val binding : ItemCoinBinding): DataBoundViewHolder<ItemPriceCoinSealed>(binding) {
    fun bindData(data : PriceCoin,callback :OnClickItem ){
        binding.txtTitle.text=data.base
        binding.txtDesc.text=data.name
        binding.txtSubTitle.text="/${data.counter}"
        binding.txtPriceBuy.text=data.buyPrice
        binding.txtPriceSell.text=data.sellPrice
        context?.let { ImageUtils.bindImage(context = it,binding.imgAvatar,data.icon) }
    }
}