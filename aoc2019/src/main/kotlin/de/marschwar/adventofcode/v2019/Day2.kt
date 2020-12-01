package de.marschwar.adventofcode.v2019
import de.marschwar.adventofcode.Puzzle

class Day2(private val replace: Boolean = true) : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val program = input.toProgram()
        return if (replace)
            program.replaceAndRun(12, 2)
        else
            program.doRun()
    }

    override fun part2(input: Sequence<String>): Any {
        val programTemplate = input.toProgram()
        (0..99).forEach { noun ->
            (0..99).forEach { verb ->
                val program = programTemplate.toMutableList()
                if (program.replaceAndRun(noun, verb) == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        return "no result"
    }

    private fun Program.replaceAndRun(nown: Int, verb: Int): Int {
        set(1, nown)
        set(2, verb)
        return doRun()
    }

    private fun Sequence<String>.toProgram(): Program =
        this.first()
            .split(",")
            .map(String::toInt)
            .toMutableList()

    private tailrec fun Program.doRun(currentIndex: Int = 0): Int {
        val instruction = get(currentIndex)
        if (instruction == 99) {
            return first()
        }
        val left = get(get(currentIndex + 1))
        val right = get(get(currentIndex + 2))
        val targetIndex = get(currentIndex + 3)
        val newValue = when (instruction) {
            1 -> left + right
            2 -> left * right
            else -> error("unexpected instruction $instruction")
        }
        set(targetIndex, newValue)
        return doRun(currentIndex + 4)
    }
}

private typealias Program = MutableList<Int>

fun main() = Day2().solve()
