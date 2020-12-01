package de.marschwar.adventofcode

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class Day1Test {

    val puzzle = Day1()

    @Test
    fun part1() {
        val inputs = listOf(
            "1721",
            "979",
            "366",
            "299",
            "675",
            "1456",
        )

        val result = puzzle.part1(inputs.asSequence())

        result shouldBe 514_579
    }

    @Test
    fun part2() {
        val inputs = listOf(
            "1721",
            "979",
            "366",
            "299",
            "675",
            "1456",
        )

        val result = puzzle.part2(inputs.asSequence())

        result shouldBe 241_861_950
    }

}