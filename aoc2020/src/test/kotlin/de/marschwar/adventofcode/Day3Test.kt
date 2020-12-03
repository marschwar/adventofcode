package de.marschwar.adventofcode

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class Day3Test {
    val puzzle = Day3()
    val inputs = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {

        val result = puzzle.part1(inputs)

        result shouldBe 7
    }

    @Test
    fun part2() {

        val result = puzzle.part2(inputs)

        result shouldBe 336
    }
}