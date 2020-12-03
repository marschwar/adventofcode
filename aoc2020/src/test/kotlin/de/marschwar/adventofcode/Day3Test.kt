package de.marschwar.adventofcode

import com.google.common.truth.Truth
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

        Truth.assertThat(result).isEqualTo(7)
    }

    @Test
    fun part2() {

        val result = puzzle.part2(inputs)

        Truth.assertThat(result).isEqualTo(336)
    }
}