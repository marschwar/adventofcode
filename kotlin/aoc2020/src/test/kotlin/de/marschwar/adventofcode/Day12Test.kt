package de.marschwar.adventofcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day12Test {

    val puzzle = Day12()
    val input = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {
        val result = puzzle.part1(input)

        assertEquals(25, result)
    }

    @Test
    fun part2() {
        val result = puzzle.part2(input)

        assertEquals(286, result)
    }
}