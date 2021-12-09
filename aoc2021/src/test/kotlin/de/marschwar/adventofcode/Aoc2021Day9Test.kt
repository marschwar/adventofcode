package de.marschwar.adventofcode

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class Aoc2021Day9Test {

    val puzzle = Aoc2021Day9()
    val inputs = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {

        val result = puzzle.part1(inputs)

        assertThat(result).isEqualTo(15)
    }

    @Test
    fun part2() {

        val result = puzzle.part2(inputs)

        assertThat(result).isEqualTo(61229)
    }
}
