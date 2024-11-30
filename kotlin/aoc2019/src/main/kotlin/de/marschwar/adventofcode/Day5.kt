package de.marschwar.adventofcode

class Day5() : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return IntCodeComputer(input.first()).run(1)
    }

    override fun part2(input: Sequence<String>): Any {
        return IntCodeComputer(input.first()).run(5)
    }
}

fun main() = Day5().solve()
