package de.marschwar.adventofcode.v2019

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class Day9Test {

    @Test
    fun part1a() {
        val input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"

        val result = Day9().part1(sequenceOf(input))

        result shouldBe 99
    }

    @Test
    fun part1b() {
        val input = "1102,34915192,34915192,7,4,7,99,0"

        val result = Day9().part1(sequenceOf(input))

        result shouldBe 1219070632396864L
    }

    @Test
    fun part1c() {
        val input = "104,1125899906842624,99"

        val result = Day9().part1(sequenceOf(input))

        result shouldBe 1125899906842624L
    }

    @Test
    fun part2() {
    }
}