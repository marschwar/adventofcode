package de.marschwar.adventofcode

class Day3 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val trees: List<List<Boolean>> =
            input.map { line ->
                line.map { it == '#' }
            }.toList()

        val grid = Grid(trees)
        var treeCount = grid.treeCountAtCurrentPos
        while (grid.movePossible) {
            grid.move()
            treeCount += grid.treeCountAtCurrentPos
        }

        return treeCount
    }

    override fun part2(input: Sequence<String>): Any {
        val trees: List<List<Boolean>> =
            input.map { line ->
                line.map { it == '#' }
            }.toList()

        var result = 1L
        listOf(Slope(1, 1), Slope(3, 1), Slope(5, 1), Slope(7, 1), Slope(1, 2)).forEach { slope ->
            val grid = Grid(trees, slope)
            var treeCount = grid.treeCountAtCurrentPos
            while (grid.movePossible) {
                grid.move()
                treeCount += grid.treeCountAtCurrentPos
            }
            result *= treeCount
        }

        return result
    }

    private class Grid(private val data: List<List<Boolean>>, private val slope: Slope = Slope(3, 1)) {
        private var pos = Position()

        val width: Int = data.first().size
        val height: Int = data.size

        val treeCountAtCurrentPos: Int
            get() = if (data[pos.y][pos.x]) 1 else 0

        val movePossible: Boolean
            get() = height > pos.y + slope.y

        fun move() {
            check(movePossible)
            pos = Position((pos.x + slope.x) % width, pos.y + slope.y)
        }
    }

    private data class Position(val x: Int = 0, val y: Int = 0)
    private data class Slope(val x: Int = 0, val y: Int = 0)
}

fun main() = Day3().solve()
