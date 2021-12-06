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
        val inputByAge = input.flatMap { it.split(",") }
            .groupBy { it }
            .map { it.key.toInt() to it.value.count().toLong() }
            .toMap()
        var ageCounts = LongArray(9) { inputByAge[it] ?: 0 }
        repeat(256) {
            ageCounts = LongArray(9) { idx ->
                when (idx) {
                    8 -> ageCounts[0]
                    6 -> ageCounts[7] + ageCounts[0]
                    else -> ageCounts[idx + 1]
                }
            }
        }
        return ageCounts.sum()
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
