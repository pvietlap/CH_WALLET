package com.bautoidem.chwallet.di

import android.content.SharedPreferences
import com.bautoidem.chwallet.network.respository.PriceRepo
import com.bautoidem.chwallet.preference.PreferenceRepo
import org.koin.dsl.module
import kotlin.math.sign

val repositoryModule = module {
    single { PriceRepo(get()) }
    single { PreferenceRepo(get<SharedPreferences>()) }
}