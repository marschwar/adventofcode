package de.marschwar.adventofcode

internal class Day3 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return input
            .map(this::parsed)
            .fold(PositionPart1()) { acc, v -> acc.apply(v.first, v.second) }
            .calculateResult()

    }

    override fun part2(input: Sequence<String>): Any {
        return input
            .map(this::parsed)
            .fold(PositionPart2()) { acc, v -> acc.apply(v.first, v.second) }
            .calculateResult()
    }

    private fun parsed(instruction: String): Pair<String, Int> {
        val (operation, amountAsString) = INSTRUCTION_REGEX.matchEntire(instruction)?.destructured
            ?: error("no match for $instruction")
        return operation to amountAsString.toInt()
    }

    private class PositionPart1(var depth: Int = 0, var horizontal: Int = 0) {

        fun apply(operation: String, amount: Int): PositionPart1 {
            when (operation) {
                "up" -> depth -= amount
                "down" -> depth += amount
                "forward" -> horizontal += amount
                else -> error("unknown operation $operation")
            }
            return this
        }
        fun calculateResult(): Int = depth * horizontal
    }

    private class PositionPart2(var depth: Int = 0, var horizontal: Int = 0, var aim: Int = 0) {
        fun apply(operation: String, amount: Int): PositionPart2 {
            when (operation) {
                "up" -> aim -= amount
                "down" -> aim += amount
                "forward" -> {
                    horizontal += amount
                    depth += (aim * amount)
                }
                else -> error("unknown operation $operation")
            }
            return this
        }

        fun calculateResult(): Int = depth * horizontal
    }

    companion object {
        private val INSTRUCTION_REGEX = """(\w+) (\d+)""".toRegex()
    }
}

fun main() = Day3().solve()
