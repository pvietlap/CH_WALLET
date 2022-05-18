package com.bautoidem.chwallet.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B: ViewDataBinding>(private val layoutId : Int): Fragment(),CoreInterface  {

    lateinit var binding : B

    var hasInitializedRootView = false
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView==null){
            val dataBinding= DataBindingUtil.inflate<B>(inflater,layoutId,container,false)
            binding=dataBinding
            binding.lifecycleOwner=this
            rootView=binding.root
        }else{
            (rootView?.parent as ViewGroup).removeView(rootView)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            with(this){
                initViews()
                initObserver()
                setListener()
                initData()
            }
        }
    }


    abstract fun initViews()

    abstract fun setListener()

    abstract fun initData()

    abstract fun initObserver()

}