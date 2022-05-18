package com.bautoidem.chwallet.ui.price_coin.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bautoidem.chwallet.data.PriceCoin
import com.bautoidem.chwallet.network.respository.PriceRepo
import com.bautoidem.chwallet.ui.price_coin.data.ItemPriceCoinSealed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

class ListPriceCoinViewModel(private val priceRepo: PriceRepo) : ViewModel() {
    private var _priceCoinList = MutableLiveData<MutableList<PriceCoin>>()
    var priceCoinList: LiveData<MutableList<PriceCoin>> = _priceCoinList

    private var dataPriceCoin = mutableListOf<PriceCoin>()

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
                }
            }
        }
    }
}