package com.bautoidem.chwallet.di

import com.bautoidem.chwallet.ui.price_coin.view.ListPriceCoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListPriceCoinViewModel(get(),get()) }
}