package de.marschwar.adventofcode

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

class Day6(val threashold: Int = 10_000) : Puzzle() {
    override fun part1(input: Sequence<String>): Any {
        val locations = parseLocations(input)
        val bounds = getBounds(locations)
        val grid = createGrid(bounds)

        val distances = grid.map { from ->
            val d = locations.map { point ->
                Distance(from, point)
            }.sortedBy { it.length }.toList()
            if (d.size == 1 || d.first().length < d[1].length) d.first() else null
        }
            .filterNotNull()
            .groupBy { it.to }

        val finiteDistances = distances.filter {
            it.value.all { distance ->
                val x = distance.from.first
                val y = distance.from.second
                x > bounds.left && x < bounds.right && y > bounds.top && y < bounds.bottom
            }
        }

        val result: Any = finiteDistances.map { it.value.size }.max() ?: 0
        return result
    }

    override fun part2(input: Sequence<String>): Any {
        val locations = parseLocations(input)
        val bounds = getBounds(locations)
        val grid = createGrid(bounds)

        val candidates = grid.filter { point ->
            locations.map { it.distanceTo(point) }.sum() < threashold
        }

        return candidates.size
    }

    private fun createGrid(bounds: Bounds): List<Pair<Int, Int>> {
        val grid = (bounds.left..bounds.right).flatMap { x ->
            (bounds.top..bounds.bottom).map { y ->
                x to y
            }
        }
        return grid
    }

    private fun getBounds(points: Sequence<Point>): Bounds {
        val bounds = points.fold(Bounds()) { acc, point ->
            Bounds(
                left = min(acc.left, point.first),
                right = max(acc.right, point.first),
                top = min(acc.top, point.second),
                bottom = max(acc.bottom, point.second)
            )
        }
        return bounds
    }

    private fun parseLocations(input: Sequence<String>): Sequence<Point> {
        return input.map { line ->
            val tokens = line.split(',')
            Point(tokens[0].trim().toInt(), tokens[1].trim().toInt())
        }
    }

    private data class Bounds(
        val left: Int = Int.MAX_VALUE,
        val right: Int = Int.MIN_VALUE,
        val top: Int = Int.MAX_VALUE,
        val bottom: Int = Int.MIN_VALUE
    )

    private data class Distance(val from: Point, val to: Point) {
        val length = from.distanceTo(to)
    }
}

private fun Point.distanceTo(other: Point): Int =
    (first - other.first).absoluteValue + (second - other.second).absoluteValue

fun main() = Day6().solve()
