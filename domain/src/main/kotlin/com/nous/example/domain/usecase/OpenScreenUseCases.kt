package com.nous.example.domain.usecase

import com.nous.example.domain.controller.MainNavigationController
import com.nous.example.domain.model.House

class OpenHomeScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToHome()
    }
}

class OpenAllCharactersScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToAllCharacters()
    }
}

class OpenStudentsScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToStudents()
    }
}

class OpenStaffScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToStaff()
    }
}

class OpenSpellsScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToSpells()
    }
}

class OpenCharactersByHouseScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<House, Unit> {

    override fun invoke(input: House) {
        navigationController.goToByHouse(input)
    }
}