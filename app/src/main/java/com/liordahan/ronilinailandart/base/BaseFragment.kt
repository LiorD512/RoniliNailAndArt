package com.liordahan.ronilinailandart.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<VM: ViewModel, VB: ViewBinding, STATE: Any? >(
    @LayoutRes val layoutId: Int
): Fragment(layoutId) {

    protected abstract val viewModel: VM
    protected abstract val binding: VB

    abstract fun initUi()
    abstract fun observeViewModelState()
    abstract fun observeViewModelEvents()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        observeViewModelState()
        observeViewModelEvents()
    }

    abstract fun updateInputsWithState(state: STATE?)

}