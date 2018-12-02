package de.codekenner.aoc2018

class Day2 : Puzzle() {
    override fun part1(input: Sequence<String>): Any {
        val stats = input
            .map { it.groupBy { it } }
            .map {
                val doubles = if (it.values.any { chars -> chars.size == 2 }) 1 else 0
                val triples = if (it.values.any { chars -> chars.size == 3 }) 1 else 0
                Pair(doubles, triples)
            }
            .fold(0 to 0) { acc, stats -> acc + stats }
        return stats.first * stats.second
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> =
        first + other.first to second + other.second

    override fun part2(input: Sequence<String>): Any {
        val inList = input.toList()
        val result = inList.mapIndexed { index, s ->
            inList.drop(index + 1).map { Stats(s, it) }.sortedBy { it.difference }
        }.map {
            it.firstOrNull()
        }
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
