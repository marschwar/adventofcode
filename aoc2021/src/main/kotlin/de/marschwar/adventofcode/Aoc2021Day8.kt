package de.marschwar.adventofcode

internal class Aoc2021Day8 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        return input.map { it.substringAfter('|').trim() }
            .flatMap { it.split(" ") }
            .map { it.length }
            .count { it in listOf(2, 3, 4, 7) }
    }

    override fun part2(input: PuzzleInput): Any {
        return input
            .map { it.substringBefore('|').trim() to it.substringAfter('|').trim() }
            .map { it.first.toCharSets() to it.second.toCharSets() }
            .map { it.first.toSignalMap() to it.second }
            .map { it.second.map { number -> it.first[number] }.joinToString("") }
            .map(String::toInt)
            .sum()
    }

    private fun String.toCharSets(): List<Set<Char>> {
        return split(" ").map { it.toSet() }
    }

    private fun List<Set<Char>>.toSignalMap(): Map<Set<Char>, Int> {
        val seven = this.first { it.size == 3 }
        val one = this.first { it.size == 2 }
        val eight = this.first { it.size == 7 }
        val four = this.first { it.size == 4 }

        val d = this
            .filter { it.size in (4..5) }
            .fold(('a'..'g').toSet()) { acc, set -> acc.intersect(set) }
            .first()
        val zero = this.first { it.size == 6 && !it.contains(d) }
        val three = this.first { it.size == 5 && it.minus(one).size == 3 }
        val nine = this.first { it.size == 6 && it.minus(three).size == 1 }
        val six = this.first { it.size == 6 && one.minus(it).size == 1 }
        val two = this.first { it.size == 5 && it.minus(nine).size == 1 }
        val five = this.first { it.size == 5 && it.minus(two).size == 2 }

        val signalMap = mapOf(
            zero to 0,
            one to 1,
            two to 2,
            three to 3,
            four to 4,
            five to 5,
            six to 6,
            seven to 7,
            eight to 8,
            nine to 9
        )
        check(signalMap.size == 10)
        return signalMap
    }
}

fun main() = Aoc2021Day8().solve()
