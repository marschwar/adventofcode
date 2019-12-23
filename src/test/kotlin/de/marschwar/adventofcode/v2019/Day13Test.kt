package de.marschwar.adventofcode.v2019

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun part1() {
        val input = "1,2,3,6,5,4"

        val result = Day13().part1(sequenceOf(input))

        result shouldBe 0
    }
}