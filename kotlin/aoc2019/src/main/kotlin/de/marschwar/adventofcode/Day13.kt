package de.marschwar.adventofcode

class Day13 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        val outputs = mutableListOf<Long>()
        val collectingListener = { value: Long ->
            outputs.add(value)
            Unit
        }
        val computer = IntCodeComputer(input.first(), collectingListener)
        computer.run(0)
        return outputs.chunked(3).count { it[2] == 2L }
    }

    override fun part2(input: PuzzleInput): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun main() {
    Day13().solve()
}