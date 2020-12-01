package de.marschwar.adventofcode

import java.util.function.Predicate

class Day4() : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        val range = input.convertToRange()

        return range.count { isValidPasswordPart1(it.toString()) }
    }

    override fun part2(input: Sequence<String>): Any {
        val range = input.convertToRange()

        return range.count { isValidPasswordPart2(it.toString()) }
    }

    private fun PuzzleInput.convertToRange(): IntRange {
        val numbers = first().split("-").map { it.toInt() }
        return (numbers.first()..numbers.last())
    }
}

private val numbersIncreasingPredicate: Predicate<String> = Predicate { s ->
    s.foldIndexed(true) { i, acc, c ->
        acc && if (i > 0) {
            s[i - 1] <= c
        } else true
    }
}

private val doubleRegex = Regex("""(\d)\1""")
private val hasAtLeastDoublePredicate: Predicate<String> = Predicate { s -> doubleRegex.containsMatchIn(s) }
private val endOfSameRegex = Regex("""(?<=(\d))(?!\1)""")
private val hasExactlyDoublePredicate: Predicate<String> =
    Predicate { s -> s.split(endOfSameRegex).any { it.length == 2 } }
private val part1Predicates = listOf(numbersIncreasingPredicate, hasAtLeastDoublePredicate)
private val part2Predicates = listOf(numbersIncreasingPredicate, hasExactlyDoublePredicate)

fun isValidPasswordPart1(string: String): Boolean = part1Predicates.all { it.test(string) }
fun isValidPasswordPart2(string: String): Boolean = part2Predicates.all { it.test(string) }

fun main() = Day4().solve()
