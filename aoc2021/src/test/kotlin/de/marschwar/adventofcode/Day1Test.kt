package de.marschwar.adventofcode

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class Day1Test {

    val puzzle = Day1()

    @Test
    fun part1() {
        val inputs = listOf(
            "199",
            "200",
            "208",
            "210",
            "200",
            "207",
            "240",
            "269",
            "260",
            "263",
        )

        val result = puzzle.part1(inputs.asSequence())

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun part2() {
        val inputs = listOf(
            "199",
            "200",
            "208",
            "210",
            "200",
            "207",
            "240",
            "269",
            "260",
            "263",
        )

        val result = puzzle.part2(inputs.asSequence())

        assertThat(result).isEqualTo(5)
    }

}