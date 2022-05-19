package com.bautoidem.chwallet.di

import `in`.co.ophio.secure.core.ObscuredPreferencesBuilder
import android.content.SharedPreferences
import com.bautoidem.chwallet.application.ChWalletApplication
import com.bautoidem.chwallet.utils.AppConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.Scope
import org.koin.dsl.module

val preferenceModule = module {
     single{ preferenceInstance() }
}


private fun Scope.preferenceInstance(): SharedPreferences {
        return ObscuredPreferencesBuilder()
            .setApplication(androidApplication())
            .obfuscateValue(true)
            .obfuscateKey(true)
            .setSharePrefFileName(AppConfig.PREFS_NAME)
            .setSecret(AppConfig.getSharePreferenceSecureKey())
            .createSharedPrefs()
}