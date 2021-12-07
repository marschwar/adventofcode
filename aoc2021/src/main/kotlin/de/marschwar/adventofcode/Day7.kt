package de.marschwar.adventofcode

import kotlin.math.abs

internal class Day7 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        val positions = input
            .flatMap { it.split(",") }
            .map(String::toInt)
        val max = positions.maxOrNull() ?: error("input invalid")
        val costs = (0..max).map { targetPosition ->
            positions.sumOf { abs(targetPosition - it) }
        }
        return costs.minOf { it }
    }

    override fun part2(input: PuzzleInput): Any {
        val positions = input
            .flatMap { it.split(",") }
            .map(String::toInt)
        val max = positions.maxOrNull() ?: error("input invalid")
        val costs = (0..max).map { targetPosition ->
            positions.sumOf {
                val diff = abs(targetPosition - it)
                diff * (diff + 1) / 2
            }
        }
        return costs.minOf { it }
    }
}

fun main() = Day7().solve()
