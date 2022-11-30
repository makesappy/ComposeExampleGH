package com.nous.example.domain.controller

import com.nous.example.domain.model.House
import com.nous.example.domain.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>
    fun goToHome()
    fun goToAllCharacters()
    fun goToStudents()
    fun goToStaff()
    fun goToByHouse(house: House)
    fun goToSpells()
    fun goToHouses()
    fun goToCharacter(name: String)
    fun goBack()
}