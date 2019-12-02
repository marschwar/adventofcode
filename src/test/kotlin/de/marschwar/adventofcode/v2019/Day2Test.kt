package de.marschwar.adventofcode.v2019

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day2Test {

    private val puzzle = Day2(replace = false)

    @ParameterizedTest
    @CsvSource(
        "1,0,0,0,99|2",
        "2,3,0,3,99|2",
        "1,1,1,4,99,5,6,0,99|30",
        delimiter = '|'
    )
    fun part1(input: String, expectedResult: Int) {
        val result = puzzle.part1(sequenceOf(input))
        result shouldBe expectedResult
    }

    @Test
    fun part2() {
    }
}