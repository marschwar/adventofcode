package de.marschwar.adventofcode.v2019

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day3Test {
    private val puzzle = Day3()

    @Test
    fun part1a() {
        val input = sequenceOf("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83")
        val result = puzzle.part1(input)
        result shouldBe 159
    }

    @Test
    fun part1b() {
        val input = sequenceOf(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
        )
        val result = puzzle.part1(input)
        result shouldBe 135
    }

    @Test
    fun part2() {
        val input = sequenceOf(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
        )
        val result = puzzle.part2(input)
        result shouldBe 410
    }
}