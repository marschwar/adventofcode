package de.marschwar.adventofcode

class Day1 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val numbers = input.map { it.toInt() }.toList()
        numbers.forEach { left ->
            numbers.forEach { right ->
                if (left + right == 2020) {
                    return left * right
                }
            }
        }
        error("no result")
    }

    override fun part2(input: Sequence<String>): Any {
        val numbers = input.map { it.toInt() }.toList()
        numbers.forEach { first ->
            numbers.forEach { second ->
                numbers.forEach { third ->
                    if (first + second + third == 2020) {
                        return first * second * third
                    }
                }
            }
        }
        error("no result")
    }

}


fun main() = Day1().solve()
