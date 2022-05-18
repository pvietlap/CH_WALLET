package com.bautoidem.chwallet.ui.price_coin.view

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bautoidem.chwallet.R
import com.bautoidem.chwallet.base.BaseFragment
import com.bautoidem.chwallet.databinding.FragmentListPriceCoinBinding
import com.bautoidem.chwallet.ui.price_coin.adapter.PriceCoinAdapter
import com.bautoidem.chwallet.ui.price_coin.data.ItemPriceCoinSealed
import kotlinx.coroutines.Deferred
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPriceCoinFragment :
    BaseFragment<FragmentListPriceCoinBinding>(R.layout.fragment_list_price_coin) {

    private var adapter: PriceCoinAdapter? = null
    private lateinit var job: Deferred<Unit>

    private val priceCoinViewModel: ListPriceCoinViewModel by viewModel()

    override fun initViews() {
        binding.recycler.layoutManager = LinearLayoutManager(activity)
    }

    override fun setListener() {
    }

    override fun initData() {
        priceCoinViewModel.getPriceCoinList("USD")
    }

    override fun initObserver() {
        priceCoinViewModel.priceCoinList.observeForever {
            if (adapter == null) {
                adapter = PriceCoinAdapter(activity) { data: Any?, type: Int ->
                    data?.let { it1 -> executeActionClick(type, it1) }
                }
                binding.recycler.adapter = adapter
            }
            val data = it.map { v -> ItemPriceCoinSealed.ItemPriceCoinDisplay(v) }
            adapter?.submitList(data)
            binding.viewLoading.viewAll.visibility = View.GONE
        }
    }

    override fun executeActionClick(action: Int, vararg callback: Any) {
    }

}