package de.marschwar.adventofcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    val puzzle = Day5()

    @Test
    fun part2() {
        val inputs = """
            BFFFBBFRRR
            FFFBBBFRRR
            BBFFBBFRLL
            """.trimIndent()
            .lineSequence()

        val result = puzzle.part1(inputs)

        assertEquals(820, result)
    }
}