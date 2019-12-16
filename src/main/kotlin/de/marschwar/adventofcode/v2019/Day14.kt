package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Puzzle
import de.marschwar.adventofcode.PuzzleInput

class Day14 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        return input.toReactions()
    }

    override fun part2(input: PuzzleInput): Any {
        return "no result"
    }

    private fun PuzzleInput.toReactions(): List<Reaction> {
        val regex =
            """(?:(\d+) (\w+))(?:, (\d+) (\w+))?(?:, (\d+) (\w+))?(?:, (\d+) (\w+))? => (?:(\d+) (\w+))""".toRegex()
        return map { regex.matchEntire(it) }
            .filterNotNull()
            .map { it.groupValues }
            .filter { it.isNotEmpty() }
            .map {
                it.drop(1)
                    .chunked(2)
                    .filter { pair -> pair.first().isNotBlank() }
                    .map { instr: List<String> ->
                        instr.last() to instr.first().toInt()
                    }
            }
            .map { row: List<Pair<String, Int>> ->
                val target = row.last().let { Cost(it.first, it.second) }
                val sources = row.dropLast(1).map { Cost(it.first, it.second) }
                Reaction(target, sources)
            }
            .toList()
    }

    private data class Reaction(val target: Cost, val source: List<Cost>)

    private data class Cost(val inputMaterial: String, val quantity: Int)
}

fun main() {
    Day14().solve()
}