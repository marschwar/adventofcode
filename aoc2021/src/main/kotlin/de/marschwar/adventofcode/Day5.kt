package de.marschwar.adventofcode

internal class Day5 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return input
            .map(this::parse)
            .filter { it.isHorizontal() || it.isVertical() }
            .countOverlaps()
    }

    override fun part2(input: Sequence<String>): Any {
        return input
            .map(this::parse)
            .countOverlaps()
    }

    private fun Sequence<Line>.countOverlaps() =
        this.map(Line::generatePointsOfLine)
            .flatten()
            .groupBy { it }
            .filter { it.value.size > 1 }
            .count()

    private data class Point(val x: Int, val y: Int)

    private data class Line(val from: Point, val to: Point) {
        fun isHorizontal() = from.y == to.y
        fun isVertical() = from.x == to.x

        fun generatePointsOfLine(): List<Point> = generatePointsBetween(mutableListOf(), from)

        private tailrec fun generatePointsBetween(acc: MutableList<Point>, current: Point): List<Point> {
            acc.add(current)
            if (current == to) {
                return acc
            }
            val next = Point(interpolate(current.x, to.x), interpolate(current.y, to.y))
            return generatePointsBetween(acc, next)
        }

        private fun interpolate(p1: Int, p2: Int) = if (p1 < p2) p1 + 1 else if (p1 > p2) p1 - 1 else p1
    }

    private fun parse(instruction: String): Line {
        val (x1, y1, x2, y2) = INSTRUCTION_REGEX.matchEntire(instruction)?.destructured ?: error("no match")
        return Line(
            Point(x1.toInt(), y1.toInt()),
            Point(x2.toInt(), y2.toInt()),
        )
    }

    companion object {
        val INSTRUCTION_REGEX = """\s*(\d+),(\d+) -> (\d+),(\d+)\s*""".toRegex()
    }
}

fun main() = Day5().solve()
