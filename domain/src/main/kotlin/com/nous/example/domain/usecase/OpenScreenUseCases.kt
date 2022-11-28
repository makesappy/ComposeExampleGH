package com.nous.example.domain.usecase

import com.nous.example.domain.controller.MainNavigationController

class OpenHomeScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToHome()
    }
}

class OpenListOfTagsScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToListOfTags()
    }
}

class OpenImgScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToImg()
    }
}

class OpenGifScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToGif()
    }
}

class OpenTextToSayScreenUseCase(
    private val navigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        navigationController.goToTextToSay()
    }
}