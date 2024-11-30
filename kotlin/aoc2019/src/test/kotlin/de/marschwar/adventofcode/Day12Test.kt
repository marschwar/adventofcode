package de.marschwar.adventofcode

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Test
    fun part1a() {
        val input = """
            <x=-1, y=0, z=2>
            <x=2, y=-10, z=-7>
            <x=4, y=-8, z=8>
            <x=3, y=5, z=-1>
        """.trimIndent()

        val result = Day12(10).part1(input.lineSequence())

        result shouldBe 179
    }

    @Test
    fun part1b() {
        val input = """
            <x=-8, y=-10, z=0>
            <x=5, y=5, z=10>
            <x=2, y=-7, z=3>
            <x=9, y=-8, z=-3>
        """.trimIndent()

        val result = Day12(100).part1(input.lineSequence())

        result shouldBe 1940
    }

    @Test
    fun part2a() {
        val input = """
            <x=-1, y=0, z=2>
            <x=2, y=-10, z=-7>
            <x=4, y=-8, z=8>
            <x=3, y=5, z=-1>
        """.trimIndent()

        val result = Day12().part2(input.lineSequence())

        result shouldBe 2772
    }

    @Test
    fun part2b() {
        val input = """
            <x=-8, y=-10, z=0>
            <x=5, y=5, z=10>
            <x=2, y=-7, z=3>
            <x=9, y=-8, z=-3>
        """.trimIndent()

        val result = Day12().part2(input.lineSequence())

        result shouldBe 4_686_774_924
    }
}