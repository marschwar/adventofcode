package de.marschwar.adventofcode

import kotlin.math.ceil
import kotlin.math.floor

class Day5 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return ids(input).maxOrNull() ?: error("no result")
    }

    override fun part2(input: Sequence<String>): Any {
        val ids = ids(input).sorted().toList()
        ids.forEachIndexed { idx, id ->
            if (ids[idx + 1] != id + 1) {
                return id + 1
            }
        }
        error("no result")
    }

    private fun ids(input: Sequence<String>) = input.map { line ->
        val row = calc(line.substring(0..6), 0..127)
        val col = calc(line.substring(7), 0..7)
        row * 8 + col
    }

    private tailrec fun calc(tail: String, bounds: IntRange): Int {
        if (tail.isEmpty()) {
            return bounds.first
        }
        return when (tail[0]) {
            'F' -> calc(tail.substring(1), bounds.first..(floor((bounds.first + bounds.last) / 2.0).toInt()))
            'B' -> calc(tail.substring(1), (ceil((bounds.first + bounds.last) / 2.0).toInt())..bounds.last)
            'L' -> calc(tail.substring(1), bounds.first..(floor((bounds.first + bounds.last) / 2.0).toInt()))
            'R' -> calc(tail.substring(1), (ceil((bounds.first + bounds.last) / 2.0).toInt())..bounds.last)
            else -> error("invalid instruction $tail")
        }
    }
}

fun main() = Day5().solve()
