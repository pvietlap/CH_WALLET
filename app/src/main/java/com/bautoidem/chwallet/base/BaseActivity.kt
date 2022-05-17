package com.bautoidem.chwallet.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(private val layoutId : Int): AppCompatActivity(),CoreInterface  {

    lateinit var binding : B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,layoutId)
        binding.lifecycleOwner=this
        setContentView(binding.root)
        with(this){
            initViews()
            initObserver()
            setListener()
            initData()
        }
    }

    abstract fun initViews()

    abstract fun setListener()

    abstract fun initData()

    abstract fun initObserver()
}