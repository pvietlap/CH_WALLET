package com.bautoidem.chwallet.di

import com.bautoidem.chwallet.network.respository.PriceRepo
import com.bautoidem.chwallet.network.service.PriceService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single<PriceService> { get<Retrofit>().create(PriceService::class.java)}
}