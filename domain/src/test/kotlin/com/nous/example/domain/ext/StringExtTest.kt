package com.nous.example.domain.ext

import io.kotest.matchers.shouldBe
import junitparams.JUnitParamsRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class StringExtTest {
    @Test
    fun `encodedAsArgument should replace whitespace with '_'`(){
        val input = "Harry Potter"
        input.encodedAsArgument shouldBe "Harry_Potter"
    }

    @Test
    fun `decodedAsArgument should replace '_' with whitespace`(){
        val input = "Harry_Potter"
        input.decodedAsArgument shouldBe "Harry Potter"
    }
}