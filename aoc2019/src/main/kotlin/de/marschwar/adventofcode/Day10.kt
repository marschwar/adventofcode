package de.marschwar.adventofcode

import kotlin.math.abs
import kotlin.math.tan

class Day10(private val laserPosition: Point = Point(0, 0)) : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        val asteroids = input.asAsteroids()

        return asteroids.map { current ->
            asteroids.minus(current).count { current.isVisible(it, asteroids) }
        }.max() ?: 0
    }

    override fun part2(input: PuzzleInput): Any {
        val asteroids = input.asAsteroids()
        val slopesToAsteroids = asteroids.groupBy { laserPosition.angle(it) }
            .map { group -> group.key to group.value.sortedBy { laserPosition.distance(it) } }
            .sortedBy { it.first }
        return slopesToAsteroids
    }

    private fun PuzzleInput.asAsteroids(): Set<Point> {
        val asteroids = mutableSetOf<Point>()
        forEachIndexed { row, line ->
            line.forEachIndexed { col, char ->
                if (char == '#') {
                    asteroids.add(col to row)
                }
            }
        }
        return asteroids
    }

    private fun Point.isVisible(to: Point, astroids: Set<Point>): Boolean {
        val slope = slope(to)
        val onSameLine = astroids.minus(setOf(this, to))
            .filter { if (first <= to.first) first <= it.first else first >= it.first }
            .filter { if (second <= to.second) second <= it.second else second >= it.second }
            .filter { other ->
                slope(other) == slope
            }
        val distance = distance(to)
        return !onSameLine.any { distance(it) < distance }
    }

    private fun Point.slope(to: Point): Double {
        if (first == to.first) {
            return if (second < to.second) Double.MIN_VALUE else Double.MAX_VALUE
        }
        val result = 1 * (second.toDouble() - to.second) / (to.first.toDouble() - first)
        return result
    }

    private fun Point.distance(to: Point): Int = abs(first - to.first) + abs(second - to.second)

    private fun Point.angle(to: Point): Double {
        val slope = slope(to)
        val result = tan(slope)
        return result
    }
}

fun main() {
    Day10().solve()
}