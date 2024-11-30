package de.marschwar.adventofcode

class Day9 : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        return IntCodeComputer(input.first()).run(1)
    }

    override fun part2(input: PuzzleInput): Any {
        return IntCodeComputer(input.first()).run(2)
    }
}

fun main() {
    Day9().solve()
}