package de.marschwar.adventofcode

internal class Aoc2021Day10 : Puzzle() {
    val scores = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )

    override fun part1(input: PuzzleInput): Any {
        return input.map { parseChunks(it) }
            .filter { it is Corrupted }
            .map { corrupted ->
                scores.getValue((corrupted as Corrupted).character)
            }.sum()
    }

    override fun part2(input: PuzzleInput): Any {
        val scores = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4,
        )
        val sortedScores = input.map { parseChunks(it) }
            .filter { it is Incomplete }
            .map { it as Incomplete }
            .map { it.closers }
            .map {
                it.fold(0L) { acc, c ->
                    acc * 5 + scores.getValue(c)
                }
            }.sorted()
            .toList()
        return sortedScores[(sortedScores.size - 1) / 2]
    }

    private fun parseChunks(line: String): CheckResult {
        val closers: MutableList<Char> = mutableListOf()
        line.forEach { char ->
            check(char in openChars || char in closeChars)
            val openIdx = openChars.indexOf(char)
            if (openIdx > -1) {
                closers.add(closeChars[openIdx])
            } else {
                if (closers.last() == char) {
                    closers.removeLast()
                } else {
                    return Corrupted(char)
                }
            }
        }
        return if (closers.isEmpty()) Complete else Incomplete(closers.reversed())
    }

    private sealed class CheckResult
    private object Complete : CheckResult()
    private data class Incomplete(val closers: List<Char>) : CheckResult()
    private data class Corrupted(val character: Char) : CheckResult()
    companion object {
        val openChars = listOf('(', '[', '{', '<')
        val closeChars = listOf(')', ']', '}', '>')
    }
}

fun main() = Aoc2021Day10().solve()
