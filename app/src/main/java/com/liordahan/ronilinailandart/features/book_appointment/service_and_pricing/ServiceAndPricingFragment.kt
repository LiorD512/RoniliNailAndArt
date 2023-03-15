package com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.liordahan.ronilinailandart.R
import com.liordahan.ronilinailandart.base.BaseFragment
import com.liordahan.ronilinailandart.databinding.FragmentServiceAndPricingBinding
import com.liordahan.ronilinailandart.extenstions.showErrorToast
import com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.adapters.ServiceAndPricingAdapter
import com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.models.ServicesAndPricing
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ServiceAndPricingFragment :
    BaseFragment<ServiceAndPricingViewModel, FragmentServiceAndPricingBinding, ServiceAndPricingState>(
        R.layout.fragment_service_and_pricing
    ), ServiceAndPricingAdapter.ServiceAndPricingAdapterListener {

    override val viewModel: ServiceAndPricingViewModel by viewModels()
    override val binding by viewBinding(FragmentServiceAndPricingBinding::bind)
    private lateinit var serviceAndPriceAdapter: ServiceAndPricingAdapter

    override fun initUi() {
        setAdapter()
    }

    private fun setAdapter(){
        binding.serviceFragRv.apply {
            serviceAndPriceAdapter = ServiceAndPricingAdapter(this@ServiceAndPricingFragment)
            adapter = serviceAndPriceAdapter
            layoutManager  = LinearLayoutManager(requireContext())
        }
    }
    override fun observeViewModelState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect { state ->
                        updateInputsWithState(state)
                    }
                }
            }
        }
    }

    override fun observeViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when (event) {
                        is ServiceAndPricingEvents.Error -> showErrorToast(event.message)
                    }
                }
            }
        }
    }

    override fun updateInputsWithState(state: ServiceAndPricingState?) {
        updateServiceAndPricing(state?.serviceAndPricing)
    }

    private fun updateServiceAndPricing(data: List<ServicesAndPricing>?){
        serviceAndPriceAdapter.submitList(data)
    }

    override fun onServiceClick(serviceId: String?) {
        val action = ServiceAndPricingFragmentDirections.toDateAndTime()
        findNavController().navigate(action)
    }

}