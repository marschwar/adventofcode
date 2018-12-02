package de.codekenner.aoc2018

class Day2 : Puzzle() {
    override fun part1(input: Sequence<String>): Any {
        val stats = input.map(String::asSequence)
            .map { it.groupBy { it } }
            .map {
                val doubles = it.values.any { chars -> chars.size == 2 }
                val triples = it.values.any { chars -> chars.size == 3 }
                Pair(doubles, triples)
            }
            .fold(0 to 0) { acc, stats ->
                val doubles = acc.first + (if (stats.first) 1 else 0)
                val triples = acc.second + (if (stats.second) 1 else 0)
                doubles to triples
            }
        return stats.first * stats.second
    }

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
