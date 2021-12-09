package de.marschwar.adventofcode

internal class Aoc2021Day9 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        val itemsPerRow = input.first().count()
        val numbers = input
            .flatMap { s -> s.chunked(1).map(String::toByte) }
            .toList()
        return numbers.filterIndexed { index, i ->
            numbers.adjacentTo(itemsPerRow, index).all { it > i }
        }.sumOf { it + 1 }
    }

    private fun List<Byte>.adjacentTo(itemsPerRow: Int, index: Int): List<Byte> {
        val others = mutableListOf<Byte>()
        if (index.mod(itemsPerRow) > 0) {
            others.add(this[index - 1])
        }
        if ((index + 1).mod(itemsPerRow) > 0) {
            others.add(this[index + 1])
        }
        if (index >= itemsPerRow) {
            others.add(this[index - itemsPerRow])
        }
        if (index < size - itemsPerRow) {
            others.add(this[index + itemsPerRow])
        }
        return others
    }
}

fun main() = Aoc2021Day9().solve()
