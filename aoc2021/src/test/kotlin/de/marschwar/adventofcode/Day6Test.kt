package de.marschwar.adventofcode

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class Day6Test {

    val puzzle = Day6()
    val inputs = """
        3,4,3,1,2
    """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {

        val result = puzzle.part1(inputs)

        assertThat(result).isEqualTo(5934)
    }

    @Test
    fun part2() {

        val result = puzzle.part2(inputs)

        assertThat(result).isEqualTo(26984457539)
    }
}
