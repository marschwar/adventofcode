package de.marschwar.adventofcode

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class Day3Test {

    val puzzle = Day3()
    val inputs = sequenceOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2",
    )

    @Test
    fun part1() {

        val result = puzzle.part1(inputs)

        assertThat(result).isEqualTo(150)
    }

    @Test
    fun part2() {

        val result = puzzle.part2(inputs)

        assertThat(result).isEqualTo(900)
    }
}
