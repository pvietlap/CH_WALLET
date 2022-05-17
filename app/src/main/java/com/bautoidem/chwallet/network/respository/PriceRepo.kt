package com.bautoidem.chwallet.network.respository

import com.bautoidem.chwallet.network.service.PriceService

class PriceRepo(private val priceService: PriceService) {
    suspend fun repoGetListPrice(currency: String)= priceService.listCoins(currency)
}