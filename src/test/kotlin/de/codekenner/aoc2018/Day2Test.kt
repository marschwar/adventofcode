package de.codekenner.aoc2018

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class Day2Test : StringSpec({
    val puzzle = Day2()
    "part 1" {
        val input = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab").asSequence()
        puzzle.part1(input) shouldBe 12
    }

    "part 2 " {
        val input = listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz").asSequence()
        puzzle.part2(input) shouldBe "fgij"
    }
})