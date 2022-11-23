package com.nous.example.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

/**
 * Base class for all view models.
 *
 * View model observes changes in application state using use cases that are injected in its constructor. It formats domain models as
 * user-friendly data (consisting mainly of primitive and primitive-like types), and emits these updates as view states. All the
 * heavy-lifting should be done by the view model, because the view layer must be kept as simple and dumb as possible. The view simply
 * renders each state update, containing no interesting logic whatsoever.
 *
 * Be careful not to push too much business or application logic into view model. Calling several use cases every time in a reaction to a
 * single user event is usually a smell - extract a new use case aggregating and hiding the other use cases.
 *
 * This implementation extends Jetpack [ViewModel], so it has access to Android's `viewModelScope`, but the subclasses should otherwise
 * be as Android-independent as possible (especially no direct reference to `Context` or other framework code is allowed). View models
 * should also be UI-toolkit independent.
 *
 * Make sure to use [launchWhenActive] instead of [viewModelScope.launch] to keep
 *
 * @param S State type which the view model emits. Must extend [AbstractViewModel.State] and must be a data class.
 * @param initialState Mandatory initial state that the view model emits.
 *
 * @see AbstractViewModel.State
 */
abstract class AbstractViewModel<S : AbstractViewModel.State>(initialState: S) : ViewModel() {
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
            if (isActive.lastOrNull() == true) {
                scope.launch(block = block)
            }
        }
    }

    /**
     * Marker interface for all view model states.
     *
     * Implementing classes must be data classes.
     *
     * States should be kept as flat as possible, and their properties should be mainly primitive or primitive-like types, so the view
     * doesn't need to format or transform the data in any way.
     *
     * @see AbstractViewModel
     */
    interface State {

        /**
         * Unified State Error object
         * @param type Error of [Type] type
         * @param stackId Unique stack id
         * @param dateTime Formatted date time string.
         * @param appVersion Application version
         * @param customTitle Custom error title. Is used only when [type] is [Type.Custom]
         * @param customDescription Custom error description. Is used only when [type] is [Type.Custom]
         * @param customPrimaryButtonTitle Custom on error primary button. Is used only when [type] is [Type.Custom]
         * @param customSecondaryButtonTitle Custom on error secondary button. Is used only when [type] is [Type.Custom]
         */
        data class Error(
            val type: Type,
            val dateTime: String,
            val appVersion: String,
            val customTitle: String = "",
            val customDescription: String = "",
            val customPrimaryButtonTitle: String = "",
            val customSecondaryButtonTitle: String? = null,
        ) {
            enum class Type {
                General,
                Timeout,
                MissingInternet
            }
        }
    }
}