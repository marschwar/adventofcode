package de.marschwar.adventofcode.v2019

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day6Test {
    private val puzzle = Day6()

    @Test
    fun countOrbits() {
        val result = puzzle.part1(
            sequenceOf(
                "B)C",
                "C)D",
                "G)H",
                "COM)B",
                "D)I",
                "E)F",
                "B)G",
                "E)J",
                "J)K",
                "D)E",
                "K)L"
            )
        )

        result shouldBe 42
    }

    @Test
    fun countOrbitalTransfers() {
        val input = sequenceOf(
            "B)C",
            "C)D",
            "G)H",
            "COM)B",
            "D)I",
            "E)F",
            "B)G",
            "E)J",
            "J)K",
            "I)SAN",
            "K)YOU",
            "D)E",
            "K)L"
        )
        val result = puzzle.part2(input)

        result shouldBe 4
    }
}