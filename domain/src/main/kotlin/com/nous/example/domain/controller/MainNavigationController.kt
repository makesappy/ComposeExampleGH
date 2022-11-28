package com.nous.example.domain.controller

import com.nous.example.domain.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>
    fun goToHome()
    fun goToListOfTags()
    fun goToImg()
    fun goToGif()
    fun goToTextToSay()
    fun goBack()
}