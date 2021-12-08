package de.marschwar.adventofcode

internal class Aoc2021Day8 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        return input.map { it.substringAfter('|').trim() }
            .flatMap { it.split(" ") }
            .map { it.length }
            .count { it in listOf(2, 3, 4, 7) }
    }
}

fun main() = Aoc2021Day8().solve()
