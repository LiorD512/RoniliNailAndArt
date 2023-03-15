package com.liordahan.ronilinailandart.features.main.home


import androidx.lifecycle.viewModelScope
import com.denzcoskun.imageslider.models.SlideModel
import com.liordahan.ronilinailandart.base.BaseViewModel
import com.liordahan.ronilinailandart.features.main.home.models.WorkingHours
import com.liordahan.ronilinailandart.features.main.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeFragmentState(
    val images: List<SlideModel>? = emptyList(),
    val workingHours: List<WorkingHours> = emptyList()
)

sealed class HomeFragmentEvents {
    data class Error(val message: String? = null) : HomeFragmentEvents()
}

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel<HomeFragmentState, HomeFragmentEvents>() {

    override val _state = MutableStateFlow(HomeFragmentState())
    override val state: StateFlow<HomeFragmentState>
        get() = _state

    override val _events = MutableSharedFlow<HomeFragmentEvents>()
    override val events: SharedFlow<HomeFragmentEvents>
        get() = _events

    init {
        getWorkingHours()
        getImages()
    }

    private fun getWorkingHours() {
        homeRepository.getWorkingHours().addOnSuccessListener {
            val workingHours = it.toObjects(WorkingHours::class.java).sortedBy { it.sortOrder }
            updateWorkingHours(workingHours = workingHours)
        }.addOnFailureListener {
            setError(it.localizedMessage)
        }
    }

    private fun getImages() {
        viewModelScope.launch {
            val images = homeRepository.getImages()
            val slideModel = images.shuffled().map { SlideModel(imageUrl = it, ) }.take(5)
            updateImages(slideModel)
        }
    }

    private fun updateImages(images: List<SlideModel>?) {
        _state.update {
            it.copy(images = images)
        }
    }

    private fun updateWorkingHours(workingHours: List<WorkingHours>) {
        _state.update {
            it.copy(workingHours = workingHours)
        }
    }

    private fun setError(message: String?) {
        viewModelScope.launch {
            _events.emit(HomeFragmentEvents.Error(message))
        }
    }


}