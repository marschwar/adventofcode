package de.codekenner.aoc2018

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class Day3Test : StringSpec({
    val puzzle = Day3()
    "part 1" {
        val input = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2").asSequence()
        puzzle.part1(input) shouldBe 4
    }
    "part 2" {
        val input = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2").asSequence()
        puzzle.part2(input) shouldBe 3
    }
})