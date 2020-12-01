package de.marschwar.adventofcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day1Test : StringSpec({
    val puzzle = Day1()
    "part 1" {
        forAll(
            row(listOf("+1", "+1", "+1"), 3),
            row(listOf("+1", "+1", "-1"), 1),
            row(listOf("-1", "-2", "-3"), -6)
        ) { input, result ->
            puzzle.part1(input.asSequence()).shouldBe(result)
        }
    }
    "part 2" {
        forAll(
            row(listOf("+1", "-1"), 0),
            row(listOf("+3", "+3", "+4", "-2", "-4"), 10),
            row(listOf("-6", "+3", "+8", "+5", "-6"), 5),
            row(listOf("+7", "+7", "-2", "-7", "-4"), 14)
        ) { input, result ->
            puzzle.part2(input.asSequence()).shouldBe(result)
        }
    }
})