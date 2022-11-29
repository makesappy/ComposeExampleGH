package com.nous.example.common

import androidx.compose.runtime.Composable

@Composable
internal fun <S : AbstractViewModel.State, VM : AbstractViewModel<S>> VM.withRegisteredLifecycle(): VM {
    LifecycleEffect(
        onStart = ::onActive,
        onStop = ::onInactive
    )

    return this
}