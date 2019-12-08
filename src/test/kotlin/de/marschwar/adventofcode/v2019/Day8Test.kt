package de.marschwar.adventofcode.v2019

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day8Test {


    @Test
    fun decode() {
        val input = "123456789012"
        val decoded = Day8.SpaceImageDecoder(3, 2).decode(input)

        println(decoded)
        decoded.size shouldBe 2
    }

    @Test
    fun addLayers() {
        val input = "0222112222120000"
        val decoded = Day8.SpaceImageDecoder(2, 2).decode(input)
        val reduced: Day8.Layer = decoded.reduce { acc, layer -> acc + layer }
        println(reduced)
    }

    @Test
    fun part1() {
        val input = "123456789012"
        val puzzle = Day8(3, 2)
        val result = puzzle.part1(sequenceOf(input))

        result shouldBe 1

    }

    @Test
    fun part2() {
    }
}