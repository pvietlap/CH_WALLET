package com.bautoidem.chwallet.application

import android.app.Application
import com.bautoidem.chwallet.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ChWalletApplication : Application() {
    companion object {
        lateinit var instance: ChWalletApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ChWalletApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    retrofitModule
                )
            )
        }
    }
}