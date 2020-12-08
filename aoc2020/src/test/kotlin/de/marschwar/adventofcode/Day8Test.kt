package de.marschwar.adventofcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day8Test {
    val puzzle = Day8()

    val input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
            """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {

        val result = puzzle.part1(input)

        assertEquals(5, result)
    }

    @Test
    fun part2() {

        val result = puzzle.part2(input)

        assertEquals(8, result)
    }
}