package com.bautoidem.chwallet.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PriceCoin(
    @SerializedName("base")
    @Expose
    val base: String? = null,
    @SerializedName("counter")
    @Expose
    val counter: String? = null,
    @SerializedName("buy_price")
    @Expose
    val buyPrice: String? = null,
    @SerializedName("sell_price")
    @Expose
    val sellPrice: String? = null,
    @SerializedName("icon")
    @Expose
    val icon: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
)