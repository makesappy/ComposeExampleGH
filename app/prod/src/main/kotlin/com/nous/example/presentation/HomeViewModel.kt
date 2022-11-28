package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.DataCategory
import com.nous.example.domain.usecase.*

class HomeViewModel(
    private val openListOfTagsScreen: OpenListOfTagsScreenUseCase,
    private val openTextToSayScreen: OpenTextToSayScreenUseCase,
    private val openImgScreen: OpenImgScreenUseCase,
    private val openGifScreen: OpenGifScreenUseCase
) : AbstractViewModel<HomeViewModel.State>(State()) {

    fun openCategory(category: DataCategory) = when (category) {
        DataCategory.ListOfTags -> openListOfTagsScreen()
        DataCategory.RandomImg -> openImgScreen()
        DataCategory.RandomGif -> openGifScreen()
        DataCategory.TextToSay -> openTextToSayScreen()
    }

    data class State(
        val categories: List<DataCategory> = DataCategory.values().toList()
    ) : AbstractViewModel.State
}