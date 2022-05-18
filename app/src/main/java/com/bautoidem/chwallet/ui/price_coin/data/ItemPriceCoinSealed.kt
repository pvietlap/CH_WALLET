package com.bautoidem.chwallet.ui.price_coin.data

import androidx.annotation.IntDef
import com.bautoidem.chwallet.R
import com.bautoidem.chwallet.data.PriceCoin

@Retention(AnnotationRetention.SOURCE)
@IntDef(
//    TypePriceCoin.ITEM_EMPTY,
//    TypePriceCoin.ITEM_LOADING,
    TypePriceCoin.ITEM_PRICE_COIN,
)
annotation class TypePriceCoin {
    companion object {
//        const val TypePriceCoin = R.layout.item_coin_empty
//        const val TypePriceCoin = R.layout.item_coin_loading
        const val ITEM_PRICE_COIN = R.layout.item_coin
    }
}

@TypePriceCoin
sealed class ItemPriceCoinSealed(val type: Int) {
//    class ItemPriceEmpty : ItemPriceCoinData(TypePriceCoin.ITEM_EMPTY)
//    class ItemPriceLoading : ItemPriceCoinData(TypePriceCoin.ITEM_LOADING)
    class ItemPriceCoinDisplay(val data: PriceCoin) : ItemPriceCoinSealed(TypePriceCoin.ITEM_PRICE_COIN)
}