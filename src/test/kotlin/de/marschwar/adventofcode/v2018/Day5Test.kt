package de.marschwar.adventofcode.v2018

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

internal class Day5Test : StringSpec({
    val puzzle = Day5()
    "part 1" {
        forall(
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