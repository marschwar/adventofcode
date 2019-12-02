package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Puzzle

class Day2(private val replace: Boolean = true) : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val program = input.toProgram()
        if (replace) {
            program.set(1, 12)
            program.set(2, 2)
        }
        execute(program)
        return program.first()
    }

    override fun part2(input: Sequence<String>): Any {
        return ""
    }

    private fun Sequence<String>.toProgram(): Program =
        this.first()
            .split(",")
            .map(String::toInt)
            .toMutableList()

    private tailrec fun execute(program: Program, currentIndex: Int = 0) {
        val instruction = program.get(currentIndex)
        if (instruction == 99) {
            return
        }
        val left = program.get(program.get(currentIndex + 1))
        val right = program.get(program.get(currentIndex + 2))
        val targetIndex = program.get(currentIndex + 3)
        val newValue = when (instruction) {
            1 -> left + right
            2 -> left * right
            else -> error("unexpected instruction $instruction")
        }
        program.set(targetIndex, newValue)
        execute(program, currentIndex + 4)
    }
}

private typealias Program = MutableList<Int>

fun main() = Day2().solve()
