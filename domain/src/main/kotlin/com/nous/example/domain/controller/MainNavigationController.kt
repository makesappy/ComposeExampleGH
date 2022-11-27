package com.nous.example.domain.controller

import com.nous.example.domain.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>
    fun goToInitial()
    fun goBack()
}