package de.marschwar.adventofcode

class Day1 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return input
            .map(String::toInt)
            .windowed(2)
            .count { window -> window[0] < window[1]}
    }

    override fun part2(input: Sequence<String>): Any {
        return input
            .map(String::toInt)
            .windowed(3)
            .map(Iterable<Int>::sum)
            .windowed(2)
            .count { window -> window[0] < window[1]}
    }
}

fun main() = Day1().solve()
