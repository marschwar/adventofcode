package de.marschwar.adventofcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {

    val puzzle = Day10()

    val inputSmall = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
            """.trimIndent()
        .lineSequence()

    val inputBig = """
                28
                33
                18
                42
                31
                14
                46
                20
                48
                47
                24
                23
                49
                45
                19
                38
                39
                11
                1
                32
                25
                35
                8
                17
                7
                9
                4
                2
                34
                10
                3
                """.trimIndent()
        .lineSequence()

    @Test
    fun part1_small() {

        val result = puzzle.part1(inputSmall)

        assertEquals(35, result)
    }

    @Test
    fun part1_big() {

        val result = puzzle.part1(inputBig)

        assertEquals(220, result)
    }

    @Test
    fun part2() {
        val result = puzzle.part2(inputBig)

        assertEquals(19208L, result)
    }
}