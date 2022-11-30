package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.ResultData
import com.nous.example.domain.model.onError
import com.nous.example.domain.model.onSuccess
import com.nous.example.domain.usecase.OnBackClickedUseCase
import com.nous.example.domain.usecase.ShowOverlayErrorUseCase
import com.nous.example.domain.usecase.invoke
import kotlinx.coroutines.flow.Flow

internal abstract class AbstractSearchViewModel<T>(
    private val showOverlayError: ShowOverlayErrorUseCase,
    private val onBackClicked: OnBackClickedUseCase
) : AbstractViewModel<AbstractSearchViewModel.State<T>>(State()) {

    abstract suspend fun get(): ResultData<List<T>>
    abstract fun search(query: String): Flow<List<T>>
    abstract fun openDetail(name: String)

    init {
        launchWhenActive {
            get().onSuccess {
                state = state.copy(data = it)
            }.onError {
                showOverlayError(it)
            }
        }
    }

    fun navigateBack() = onBackClicked()

    data class State<T>(
        val data: List<T> = listOf()
    ) : AbstractViewModel.State
}