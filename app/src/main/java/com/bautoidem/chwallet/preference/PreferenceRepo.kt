package com.bautoidem.chwallet.preference

import android.content.SharedPreferences
import com.bautoidem.chwallet.di.preferenceModule

class PreferenceRepo(private val preferenceService : SharedPreferences) {
    private val TEXT_KEY="TEXT_KEY"

    fun getTextKey() = preferenceService.getString(TEXT_KEY,"")

    fun setTextKey(textKey : String){
        preferenceService.edit().putString(TEXT_KEY,textKey).apply()
    }
}