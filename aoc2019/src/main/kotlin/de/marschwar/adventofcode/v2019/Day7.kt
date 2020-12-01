package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Puzzle
import de.marschwar.adventofcode.PuzzleInput

class Day7() : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        val computer = IntCodeComputer(input.first())

        return (0L..4L).permutate()
            .map { systemInputs: List<Long> ->
                systemInputs.fold(0L) { acc, phase -> computer.run(phase, acc) }
            }
            .max() ?: throw IllegalStateException("No permutations")
    }

    override fun part2(input: PuzzleInput): Any {
        val computer = IntCodeComputer(input.first())

        return (5L..9L).permutate()
            .map { systemInputs ->
                systemInputs.fold(0L) { acc, phase -> computer.run(phase, acc) }
            }
            .max() ?: throw IllegalStateException("No permutations")
    }

    private fun LongRange.permutate() = toList().permutate()

    private fun <T> List<T>.permutate(): List<List<T>> {
        if (size == 1) return listOf(this)

        return flatMap { element ->
            minus(element).permutate().map { listOf(element) + it }
        }
    }
}

fun main() = Day7().solve()
