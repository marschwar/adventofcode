package de.marschwar.adventofcode

class Day11(val maxEmpty: Int = 3) : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        var currentFloorPlan = FloorPlan.of(input, maxEmpty)
        var next = currentFloorPlan.update()
        while (currentFloorPlan != next) {
            currentFloorPlan = next
            next = currentFloorPlan.update()
        }
        return currentFloorPlan.areas.count(Area::occupied)
    }

    override fun part2(input: Sequence<String>): Any {
        error("no result")
    }

    private data class Layout(val x: Int, val y: Int)

    private data class FloorPlan(val layout: Layout, val areas: List<Area>, val maxEmpty: Int) {
        override fun toString(): String = areas.toString(layout)
        fun update(): FloorPlan {
            val nextAreas = areas.mapIndexed { index, area ->
                area.update(
                    index
                        .toPosition(layout)
                        .adjacent(layout)
                        .map { it.toIndex(layout) }
                        .map { areas[it] },
                    maxEmpty
                )
            }
            return copy(areas = nextAreas)
        }

        companion object {
            fun of(input: Sequence<String>, maxEmpty: Int): FloorPlan {
                var width = 0
                val areas = input.flatMap { line ->
                    width = line.length
                    line.map { Area.of(it) }
                }.toList()

                return FloorPlan(Layout(width, areas.size / width), areas, maxEmpty)
            }
        }

        private fun Int.toPosition(layout: Layout): Position {
            val x = this % layout.x
            val y = this / layout.x
            return Position(x, y)
        }

        private fun Position.toIndex(layout: Layout) = y * layout.x + x
        private fun Position.isValid(layout: Layout) = x >= 0 && y >= 0 && x < layout.x && y < layout.y

        private fun Position.adjacent(layout: Layout): List<Position> {
            return listOf(
                Position(x - 1, y),
                Position(x - 1, y - 1),
                Position(x, y - 1),
                Position(x + 1, y - 1),
                Position(x + 1, y),
                Position(x + 1, y + 1),
                Position(x, y + 1),
                Position(x - 1, y + 1),
            ).filter { it.isValid(layout) }
        }

        private fun List<Area>.toString(layout: Layout) = chunked(layout.x).joinToString("\n")
    }

    private sealed class Area {
        abstract fun update(adjacentAreas: List<Area>, maxEmpty: Int): Area
        open val occupied: Boolean = false

        private object Occupied : Area() {
            override val occupied: Boolean = true
            override fun update(adjacentAreas: List<Area>, maxEmpty: Int): Area =
                if (adjacentAreas.count { it == Occupied } > maxEmpty) Empty else Occupied

            override fun toString() = "#"
        }

        private object Empty : Area() {
            override fun update(adjacentAreas: List<Area>, maxEmpty: Int): Area =
                if (adjacentAreas.none { it == Occupied }) Occupied else Empty

            override fun toString() = "L"
        }

        private object Floor : Area() {
            override fun update(adjacentAreas: List<Area>, maxEmpty: Int) = Floor

            override fun toString() = "."
        }

        companion object {
            fun of(char: Char): Area = when (char) {
                '#' -> Occupied
                '.' -> Floor
                'L' -> Empty
                else -> error("invalid character '$char'")
            }
        }
    }
}


fun main() = Day11().solve()
