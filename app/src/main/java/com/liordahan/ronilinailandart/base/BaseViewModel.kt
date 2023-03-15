package com.liordahan.ronilinailandart.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseViewModel<STATE: Any, EVENTS: Any>: ViewModel() {

    protected abstract val _state: MutableStateFlow<STATE>
    abstract val state: StateFlow<STATE>

    protected abstract val _events: MutableSharedFlow<EVENTS>
    abstract val events: SharedFlow<EVENTS>

}