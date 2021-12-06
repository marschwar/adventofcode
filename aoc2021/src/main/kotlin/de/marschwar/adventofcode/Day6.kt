package de.marschwar.adventofcode

internal class Day6 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        return input
            .flatMap { it.split(",") }
            .map { Fish(it.toInt()) }
            .toList()
            .advance(80)
            .count()
    }
    override fun part2(input: PuzzleInput): Any {
      TODO()
    }

    @JvmInline
    private value class Fish(val timer: Int = 8) {
        fun tick(): List<Fish> {
            return if (timer == 0) listOf(Fish(6), Fish(8)) else listOf(Fish(timer - 1))
        }
    }

    private tailrec fun List<Fish>.advance(ticks: Int): List<Fish> {
        if (ticks == 0) {
            return this
        }
        return flatMap { it.tick() }.advance(ticks - 1)
    }

}

fun main() = Day6().solve()
