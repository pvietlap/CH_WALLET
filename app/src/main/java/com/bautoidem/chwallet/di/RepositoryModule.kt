package com.bautoidem.chwallet.di

import com.bautoidem.chwallet.network.respository.PriceRepo
import org.koin.dsl.module

val repositoryModule = module {
    single { PriceRepo(get()) }
}