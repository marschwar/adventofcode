package de.marschwar.adventofcode.v2019

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class Day14Test {
    val puzzle = Day14()

    @Test
    fun part1a() {
        val input = """
            10 ORE => 10 A
            1 ORE => 1 B
            7 A, 1 B => 1 C
            7 A, 1 C => 1 D
            7 A, 1 D => 1 E
            7 A, 1 E => 1 FUEL
        """.trimIndent()

        val result = puzzle.part1(input.lineSequence())

        result shouldBe 31
    }

    @Test
    fun part1b() {
        val input = """
            9 ORE => 2 A
            8 ORE => 3 B
            7 ORE => 5 C
            3 A, 4 B => 1 AB
            5 B, 7 C => 1 BC
            4 C, 1 A => 1 CA
            2 AB, 3 BC, 4 CA => 1 FUEL
        """.trimIndent()

        val result = puzzle.part1(input.lineSequence())

        result shouldBe 165
    }
}