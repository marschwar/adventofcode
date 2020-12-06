package de.marschwar.adventofcode

class Day6 : Puzzle(removeBlankLines = false) {

    override fun part1(input: Sequence<String>): Any {
        val result = mutableListOf<Set<Char>>()
        var current = mutableSetOf<Char>()
        input.forEach { line ->
            if (line.isEmpty()) {
                result.add(current)
                current = mutableSetOf()
            } else {
                current.addAll(line.toSet())
            }
        }
        result.add(current)

        return result.sumBy { it.size }
    }

    override fun part2(input: Sequence<String>): Any {
        val result = mutableListOf<Set<Char>>()
        var current: Set<Char>? = null
        input.forEach { line ->
            if (line.isEmpty()) {
                current?.let { result.add(it) }
                current = null
            } else {
                if (current == null) {
                    current = line.toSet()
                } else {
                    current = current?.intersect(line.toSet())
                }
            }
        }
        current?.let { result.add(it) }

        return result.sumBy { it.size }
    }
}

fun main() = Day6().solve()
