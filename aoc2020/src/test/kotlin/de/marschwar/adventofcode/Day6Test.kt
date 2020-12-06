package de.marschwar.adventofcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {
    val puzzle = Day6()
    val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent().lineSequence()

    @Test
    fun part1() {

        val result = puzzle.part1(input)

        assertEquals(11, result)
    }

    @Test
    fun part2() {

        val result = puzzle.part2(input)

        assertEquals(6, result)
    }
}