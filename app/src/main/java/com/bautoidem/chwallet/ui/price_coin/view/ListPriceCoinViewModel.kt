package com.bautoidem.chwallet.ui.price_coin.view

import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bautoidem.chwallet.data.PriceCoin
import com.bautoidem.chwallet.encryption.TripleDES
import com.bautoidem.chwallet.network.respository.PriceRepo
import com.bautoidem.chwallet.preference.PreferenceRepo
import com.bautoidem.chwallet.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ListPriceCoinViewModel(private val priceRepo: PriceRepo,private val preference : PreferenceRepo) : ViewModel() {
    private var _priceCoinList = MutableLiveData<MutableList<PriceCoin>>()
    var priceCoinList: LiveData<MutableList<PriceCoin>> = _priceCoinList

    private var _keyText = MutableLiveData<String>()
    var keyText : LiveData<String> = _keyText

    private var dataPriceCoin = mutableListOf<PriceCoin>()

    fun getKeyText(){
        val key=preference.getTextKey()
        if (key?.isBlank()==true){ // check key has exist
            val keyEncrypt=TripleDES.encrypt(AppConfig.key())  //encrypt data by DES
            val keyBase64=  Base64.encodeToString(keyEncrypt,Base64.DEFAULT)  // encode key by Base64
            preference.setTextKey(keyBase64) // save key encode by preference
        }
        val keyBase64 = preference.getTextKey() // get key from preference
        val keyDecode=Base64.decode(keyBase64,Base64.DEFAULT)  // decode Base64 key
        val keyDecrypt= TripleDES.decrypt(keyDecode) // decrypt key by DES
        _keyText.postValue(keyDecrypt)
    }

    fun searchCoin(keySearch: String) {
        val temp = mutableListOf<PriceCoin>()
        temp.addAll(dataPriceCoin)
        if (keySearch.isBlank()) {
            _priceCoinList.postValue(temp)
        } else {
            _priceCoinList.postValue(temp.filter { it.name?.lowercase(Locale.getDefault())?.contains(keySearch)==true || it.base?.lowercase(Locale.getDefault())?.contains(keySearch)==true }.toMutableList())
        }
    }

    fun getPriceCoinList(currency: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val data = priceRepo.repoGetListPrice(currency).list
                    _priceCoinList.postValue(data)
                    dataPriceCoin = data
                } catch (e: Exception) {
                    e.message?.let { Log.d("Error", it) }
                    _priceCoinList.postValue(mutableListOf())
                }
            }
        }
    }
}