package de.marschwar.adventofcode

class Day2 : Puzzle() {
    override fun part1(input: Sequence<String>): Any {
        val duplicateCounters = input
            .map { it.groupingBy { it }.eachCount().values }
        return duplicateCounters.count { 2 in it } * duplicateCounters.count { 3 in it }
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> =
        first + other.first to second + other.second

    override fun part2(input: Sequence<String>): Any {
        val inList = input.toList()
        val result = inList.mapIndexed { index, s ->
            inList.drop(index + 1).map { Stats(s, it) }.sortedBy { it.difference }
        }
            .map(List<Stats>::firstOrNull)
            .filterNotNull()
            .sortedBy { it.difference }
            .first()
        return result.box.filterIndexed { index, c -> c == result.other[index] }
    }

    private data class Stats(val box: String, val other: String) {
        val difference: Int by lazy {
            box.zip(other).count { it.first == it.second }
        }
    }
}

fun main() = Day2().solve()
