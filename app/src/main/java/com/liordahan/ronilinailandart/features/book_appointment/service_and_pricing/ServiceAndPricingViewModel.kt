package com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing

import androidx.lifecycle.viewModelScope
import com.liordahan.ronilinailandart.base.BaseViewModel
import com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.models.ServicesAndPricing
import com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.repository.ServiceAndPricingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ServiceAndPricingState(
    val serviceAndPricing: List<ServicesAndPricing> = emptyList()
)

sealed class ServiceAndPricingEvents{
    data class Error(val message: String? = null): ServiceAndPricingEvents()
}

@HiltViewModel
class ServiceAndPricingViewModel @Inject constructor(
    private val serviceAndPricingRepository: ServiceAndPricingRepository
): BaseViewModel<ServiceAndPricingState, ServiceAndPricingEvents>() {

    override val _state = MutableStateFlow(ServiceAndPricingState())
    override val state: StateFlow<ServiceAndPricingState>
        get() = _state

    override val _events = MutableSharedFlow<ServiceAndPricingEvents>()
    override val events: SharedFlow<ServiceAndPricingEvents>
        get() = _events

    init {
        getServiceAndPricing()
    }

    private fun getServiceAndPricing(){
        serviceAndPricingRepository.getServiceAndPricing().addOnSuccessListener {
            val serviceAndPricingList = it.toObjects(ServicesAndPricing::class.java).sortedBy { it.sortOrder }
            updateServiceAndPricing(serviceAndPricingList)
        }.addOnFailureListener {
            setError(it.localizedMessage)
        }
    }

    private fun updateServiceAndPricing(serviceAndPricing: List<ServicesAndPricing>){
        _state.update {
            it.copy(serviceAndPricing = serviceAndPricing)
        }
    }

    private fun setError(message: String?) {
        viewModelScope.launch {
            _events.emit(ServiceAndPricingEvents.Error(message))
        }
    }
}