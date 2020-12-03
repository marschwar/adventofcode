package de.marschwar.adventofcode

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

internal class Day2Test {

    val puzzle = Day2()
    val inputs = sequenceOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc",
    )

    @Test
    fun part1() {

        val result = puzzle.part1(inputs)

        Truth.assertThat(result).isEqualTo(2)
    }

    @Test
    fun part2() {
        val result = puzzle.part2(inputs)

        Truth.assertThat(result).isEqualTo(1)

    }

}