package de.marschwar.adventofcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day9Test {
    val puzzle = Day9(5)
    val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
            """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {
        val result = puzzle.part1(input)

        assertEquals(127L, result)
    }

    @Test
    fun part2() {
        val result = puzzle.part2(input)

        assertEquals(62L, result)
    }
}