package de.marschwar.adventofcode.v2018

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class Day6Test : StringSpec({
    val input = sequenceOf("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9")
    val puzzle = Day6(32)
    "part 1" {
        puzzle.part1(input) shouldBe 17
    }
    "part 2" {
        puzzle.part2(input) shouldBe 16
    }
})
