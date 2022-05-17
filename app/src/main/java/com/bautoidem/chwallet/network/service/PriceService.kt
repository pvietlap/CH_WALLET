package com.bautoidem.chwallet.network.service

import com.bautoidem.chwallet.data.ComponentResponse
import com.bautoidem.chwallet.data.PriceCoin
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PriceService {
//    @Headers("Accept: application/json")
    @GET("price/all_prices_for_mobile")
    suspend fun listCoins(@Query("counter_currency") currency: String?): ComponentResponse<PriceCoin>
}