package com.bautoidem.chwallet.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ComponentResponse<T : Any>(
    @SerializedName("data")
    @Expose
    val list: List<T>
)