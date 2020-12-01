package de.marschwar.adventofcode

class Day5 : Puzzle() {
    override fun part1(input: Sequence<String>): Any {
        return input.first().toList().implode().size
    }

    override fun part2(input: Sequence<String>): Any {
        val origInput = input.first().toList()
        val units = origInput.map(Char::toLowerCase).distinct()
        val candidates = units.map { unit ->
            origInput.filterNot { it.toLowerCase() == unit }.implode()
        }
        return candidates.map { it.size }.min() ?: 0
    }
}

private fun List<Char>.implode() =
    fold(mutableListOf<Char>()) { acc, c ->
        if (!acc.isEmpty() && acc.last().isDestroyedBy(c)) {
            acc.removeAt(acc.size - 1)
        } else {
            acc.add(c)
        }
        acc
    }

private fun Char.isDestroyedBy(other: Char) =
    this.toUpperCase() == other.toUpperCase() && this.isLowerCase() != other.isLowerCase()

fun main() = Day5().solve()
