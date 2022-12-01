package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Data
import com.nous.example.domain.model.House
import com.nous.example.domain.repository.CharacterRepository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junitparams.JUnitParamsRunner
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class GetCharacterByHouseUseCaseTest {
    @Test
    fun `UC should return data from repository`() = runTest {
        val input = House.Gryffindor
        val output = Data.Success(listOf<Character>(mockk()))
        val repo = mockk<CharacterRepository> {
            coEvery { this@mockk.getCharactersByHouse(input) } returns output
        }
        val uc = GetCharactersByHouseUseCase(repo)
        uc(input) shouldBe output
        coVerify(exactly = 1) {
            repo.getCharactersByHouse(input)
        }
    }
}