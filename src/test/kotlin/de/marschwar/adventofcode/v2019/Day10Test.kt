package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Point
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day10Test {
    private val puzzle = Day10()

    @Test
    fun part1a() {
        val input = """
                    |......#.#.
                    |#..#.#....
                    |..#######.
                    |.#.#.###..
                    |.#..#.....
                    |..#....#.#
                    |#..#....#.
                    |.##.#..###
                    |##...#..#.
                    |.#....####
                    |""".trimMargin()

        val result = puzzle.part1(input.lineSequence())
        result shouldBe 33
    }

    @Test
    fun part1b() {
        val input = """
                    #.#...#.#.
                    .###....#.
                    .#....#...
                    ##.#.#.#.#
                    ....#.#.#.
                    .##..###.#
                    ..#...##..
                    ..##....##
                    ......#...
                    .####.###.
                    """.trimIndent()

        val result = puzzle.part1(input.lineSequence())
        result shouldBe 35
    }

    @Test
    fun part1c() {
        val input = """
                    .#..#..###
                    ####.###.#
                    ....###.#.
                    ..###.##.#
                    ##.##.#.#.
                    ....###..#
                    ..#.#..#.#
                    #..#.#.###
                    .##...##.#
                    .....#.#..
                    """.trimIndent()

        val result = puzzle.part1(input.lineSequence())
        result shouldBe 41
    }

    @Test
    fun part1d() {
        val input = """
                    .#..##.###...#######
                    ##.############..##.
                    .#.######.########.#
                    .###.#######.####.#.
                    #####.##.#.##.###.##
                    ..#####..#.#########
                    ####################
                    #.####....###.#.#.##
                    ##.#################
                    #####.##.###..####..
                    ..######..##.#######
                    ####.##.####...##..#
                    .#####..#.######.###
                    ##...#.##########...
                    #.##########.#######
                    .####.#.###.###.#.##
                    ....##.##.###..#####
                    .#.#.###########.###
                    #.#.#.#####.####.###
                    ###.##.####.##.#..##
                    """.trimIndent()

        val result = puzzle.part1(input.lineSequence())
        result shouldBe 210
    }

    @Test
    fun part2() {
        val puzzle = Day10(Point(8, 3))
        val input = """
                    .#....#####...#..
                    ##...##.#####..##
                    ##...#...#.#####.
                    ..#.....X...###..
                    ..#.#.....#....##
                    """.trimIndent()

        val result = puzzle.part2(input.lineSequence())

        result shouldBe 802
    }
}