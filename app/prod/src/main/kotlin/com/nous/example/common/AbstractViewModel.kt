package com.nous.example.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nous.example.domain.model.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

/**
 * Make sure to use [launchWhenActive] instead of [viewModelScope.launch]
 *
 * @param S State type which the view model emits. Must extend [AbstractViewModel.State] and must be a data class.
 * @param initialState Mandatory initial state that the view model emits.
 *
 * @see AbstractViewModel.State
 */
internal abstract class AbstractViewModel<S : AbstractViewModel.State>(initialState: S) : ViewModel() {
    private val mutableStates = MutableStateFlow(initialState)
    private val isActive = MutableStateFlow(false)

    /**
     * It is used to union all coroutines so that we can simply stop them all at once when exiting the screen in [onInactive] method
     */
    private val scope: CoroutineScope =
        MainScope() + SupervisorJob(viewModelScope.coroutineContext[Job])

    /**
     * View states emitted by this view model.
     *
     * The view is responsible for rendering these states. Do not access this property from the view model implementation (use the `state`
     * property for that).
     */
    val states: StateFlow<S> = mutableStates.asStateFlow()

    /**
     * View model's internal state, presented as mutable property. Writing a different value to this property causes the public observable
     * `states` flow to emit new value.
     */
    protected var state: S
        get() = mutableStates.value
        set(value) {
            mutableStates.value = value
        }

    /**
     * Called every time when screen is displayed.
     * ViewModel must be initialized with [withRegisteredLifecycle] extension
     */
    fun onActive() {
        isActive.value = true
    }

    /**
     * Called every time when screen is hidden
     * ViewModel must be initialized with [withRegisteredLifecycle] extension
     */
    internal fun onInactive() {
        isActive.value = false
        scope.coroutineContext.cancelChildren()
    }

    /**
     * Use this instead of [viewModelScope.launch]
     */
    protected fun launchWhenActive(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            isActive.filter { it }.collect {
                scope.launch(block = block)
            }
        }
    }

    /**
     * Marker interface for all view model states.
     */
    interface State
}