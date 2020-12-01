package de.marschwar.adventofcode.v2018

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Day5Test : StringSpec({
    val puzzle = Day5()
    "part 1" {
        forAll(
            row("aA", 0),
            row("BaAb", 0),
            row("AaABaAb", 1),
            row("aBAb", 4),
            row("aaBBA", 5),
            row("dabAcCaCBAcCcaDA", 10)
        ) { input, result ->
            puzzle.part1(sequenceOf(input)) shouldBe result
        }

    }
    "part 2" {
        puzzle.part2(sequenceOf("dabAcCaCBAcCcaDA")) shouldBe 4
    }
})