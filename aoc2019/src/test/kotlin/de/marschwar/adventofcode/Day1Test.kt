package de.marschwar.adventofcode

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class Day1Test {

    val puzzle = Day1()
    @Test
    fun part1() {
        val inputs = listOf("12", "14", "1969", "100756")
        val fuelRequirements = listOf(2, 2, 654, 33583)

        val result = puzzle.part1(inputs.asSequence())

        result shouldBe fuelRequirements.sum()
    }

    @Test
    fun part2() {
        val inputs = listOf("12", "14", "1969", "100756")
        val fuelRequirements = listOf(2, 2, 966, 50346)

        val result = puzzle.part2(inputs.asSequence())

        result shouldBe fuelRequirements.sum()
    }
}