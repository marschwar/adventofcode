package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Puzzle
import de.marschwar.adventofcode.PuzzleInput

class Day11 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        val computer = IntCodeComputer(input.first())
        return computer.run(1)
    }

    override fun part2(input: PuzzleInput): Any {
        return "no result"
    }
}

fun main() {
    Day11().solve()
}