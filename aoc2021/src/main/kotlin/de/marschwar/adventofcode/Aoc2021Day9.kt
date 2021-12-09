package de.marschwar.adventofcode

internal class Aoc2021Day9 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        return HeightMap.parse(input)
            .findLowPoints()
            .sumOf { it + 1 }
    }

    private class HeightMap(private val itemsPerRow: Int, private val heights: List<Byte>) {

        fun findLowPoints(): List<Byte> {
            return findLowIndexes().map { heights[it] }
        }

        fun findLowIndexes(): List<Int> {
            return heights.mapIndexed { index, byte ->
                if (adjacentTo(index).all { it > byte }) index else null
            }.filterNotNull()
        }

        fun adjacentTo(index: Int): List<Byte> {
            val others = mutableListOf<Byte>()
            if (index.mod(itemsPerRow) > 0) {
                others.add(heights[index - 1])
            }
            if ((index + 1).mod(itemsPerRow) > 0) {
                others.add(heights[index + 1])
            }
            if (index >= itemsPerRow) {
                others.add(heights[index - itemsPerRow])
            }
            if (index < heights.size - itemsPerRow) {
                others.add(heights[index + itemsPerRow])
            }
            return others
        }

        companion object {
            fun parse(input: Sequence<String>): HeightMap {
                val itemsPerRow = input.first().count()
                val numbers = input
                    .flatMap { s -> s.chunked(1).map(String::toByte) }
                    .toList()
                return HeightMap(itemsPerRow, numbers)
            }
        }
    }
}

fun main() = Aoc2021Day9().solve()
