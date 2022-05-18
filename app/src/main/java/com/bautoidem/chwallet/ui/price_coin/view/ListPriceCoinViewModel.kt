package com.bautoidem.chwallet.ui.price_coin.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bautoidem.chwallet.data.PriceCoin
import com.bautoidem.chwallet.network.respository.PriceRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ListPriceCoinViewModel(private val priceRepo: PriceRepo) : ViewModel() {
    private var _priceCoinList = MutableLiveData<MutableList<PriceCoin>>()
    var priceCoinList: LiveData<MutableList<PriceCoin>> = _priceCoinList


    fun getPriceCoinList(currency: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                   val data= priceRepo.repoGetListPrice(currency).list
                    _priceCoinList.postValue(data)
                } catch (e: Exception) {
                    e.message?.let { Log.d("Error", it) }
                }
            }
        }
    }
}