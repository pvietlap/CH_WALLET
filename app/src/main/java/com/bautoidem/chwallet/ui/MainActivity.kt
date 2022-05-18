package com.bautoidem.chwallet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bautoidem.chwallet.R
import com.bautoidem.chwallet.base.BaseActivity
import com.bautoidem.chwallet.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var naviControl: NavController

    override fun initViews() {
        binding.lifecycleOwner = this
        naviControl =
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController

    }

    override fun setListener() {
    }

    override fun initData() {
    }

    override fun initObserver() {
    }

    override fun executeActionClick(action: Int, vararg callback: Any) {
    }

}