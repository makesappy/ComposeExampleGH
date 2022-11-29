package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.DataCategory
import com.nous.example.domain.usecase.*

class HomeViewModel(
    private val openListOfTagsScreen: OpenAllCharactersScreenUseCase,
    private val openTextToSayScreen: OpenSpellsScreenUseCase,
    private val openImgScreen: OpenStudentsScreenUseCase,
    private val openGifScreen: OpenStaffScreenUseCase
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