package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Data
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
internal class GetCharacterByNameUseCaseTest {
    @Test
    fun `UC should return data from repository`() = runTest {
        val input = "name"
        val output = Data.Success(mockk<Character>())
        val repo = mockk<CharacterRepository> {
            coEvery { this@mockk.getCharacter(input) } returns output
        }
        val uc = GetCharacterByNameUseCase(repo)
        uc(input) shouldBe output
        coVerify(exactly = 1) {
            repo.getCharacter(input)
        }
    }
}