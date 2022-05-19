package com.bautoidem.chwallet.ui.price_coin.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.bautoidem.chwallet.R
import com.bautoidem.chwallet.base.BaseFragment
import com.bautoidem.chwallet.databinding.FragmentListPriceCoinBinding
import com.bautoidem.chwallet.ui.price_coin.adapter.PriceCoinAdapter
import com.bautoidem.chwallet.ui.price_coin.data.ItemPriceCoinSealed
import com.bautoidem.chwallet.utils.toGone
import com.bautoidem.chwallet.utils.toVisible
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListPriceCoinFragment :
    BaseFragment<FragmentListPriceCoinBinding>(R.layout.fragment_list_price_coin) {

    private var adapter: PriceCoinAdapter? = null
    private var isSearch = false

    private val priceCoinViewModel: ListPriceCoinViewModel by viewModel()

    override fun initViews() {
        binding.recycler.layoutManager = LinearLayoutManager(activity)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun setListener() {
        binding.imgSearch.setOnClickListener {
            setUISearch()
        }
        binding.imgClose.setOnClickListener {
            setUISearch()
        }
        binding.edtSearch.doAfterTextChanged {
            priceCoinViewModel.searchCoin(it.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUISearch() {
        isSearch = !isSearch
        if (isSearch) {
            binding.imgSearch.toGone()
            binding.viewSearch.toVisible()
            binding.edtSearch.requestFocus()
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        } else {
            binding.edtSearch.setText("")
            binding.imgSearch.toVisible()
            binding.viewSearch.toGone()
            val imm: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.edtSearch.windowToken, 0)
        }
    }

    override fun initData() {
        priceCoinViewModel.getPriceCoinList("USD")
        priceCoinViewModel.getKeyText()
    }

    override fun initObserver() {
        priceCoinViewModel.priceCoinList.observe(this) {
            if (adapter == null) {
                adapter = PriceCoinAdapter(activity) { data: Any?, type: Int ->
                    data?.let { it1 -> executeActionClick(type, it1) }
                }
                binding.recycler.adapter = adapter
            }
            if (it.isNullOrEmpty()){
                val empty= mutableListOf<ItemPriceCoinSealed>(ItemPriceCoinSealed.ItemPriceEmpty())
                adapter?.submitList(empty)
            }else{
                val data = it.map { v -> ItemPriceCoinSealed.ItemPriceCoinDisplay(v) }
                adapter?.submitList(data)
            }
            binding.viewLoading.viewAll.visibility = View.GONE
        }
        priceCoinViewModel.keyText.observe(this){
            Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
        }
    }

    override fun executeActionClick(action: Int, vararg callback: Any) {
    }

}