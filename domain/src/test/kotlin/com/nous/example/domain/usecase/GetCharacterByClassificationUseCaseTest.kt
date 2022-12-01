package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
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

/**
 * Example of unit-test for domain layer
 */
@RunWith(JUnitParamsRunner::class)
internal class GetCharacterByClassificationUseCaseTest {
    @Test
    fun `UC should return data from repository`() = runTest {
        val input = Classification.Student
        val output = Data.Success(listOf<Character>(mockk()))
        val repo = mockk<CharacterRepository> {
            coEvery { this@mockk.getCharactersByClassification(input) } returns output
        }
        val uc = GetCharactersByClassificationUseCase(repo)
        uc(input) shouldBe output
        coVerify(exactly = 1) {
            repo.getCharactersByClassification(input)
        }
    }
}