package de.marschwar.adventofcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {
    val puzzle = Day11()
    val input = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
         """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {
        val result = puzzle.part1(input)
        assertEquals(37, result)
    }

    @Test
    fun part2() {
    }
}