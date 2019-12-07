package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Puzzle
import de.marschwar.adventofcode.PuzzleInput

class Day7() : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        val computer = IntCodeComputer(input.first())

        return (0..4).permutate()
            .map { systemInputs ->
                systemInputs.fold(0) { acc, phase -> computer.run(phase, acc) }
            }
            .max() ?: throw IllegalStateException("No permutations")
    }

    override fun part2(input: PuzzleInput): Any {
        return IntCodeComputer(input.first()).run(1)
    }

    private fun IntRange.permutate() = toList().permutate()

    private fun <T> List<T>.permutate(): List<List<T>> {
        if (size == 1) return listOf(this)

        return flatMap { element ->
            minus(element).permutate().map { listOf(element) + it }
        }
    }
}

fun main() = Day7().solve()
