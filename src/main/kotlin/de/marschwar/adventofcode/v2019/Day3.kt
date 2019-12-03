package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Point
import de.marschwar.adventofcode.Puzzle
import de.marschwar.adventofcode.v2019.Direction.D
import de.marschwar.adventofcode.v2019.Direction.L
import de.marschwar.adventofcode.v2019.Direction.R
import de.marschwar.adventofcode.v2019.Direction.U
import kotlin.math.absoluteValue

class Day3() : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return input
            .map { it.asDirections() }
            .map { instructions ->
                instructions.fold(mutableListOf(Point(0, 0))) { trail, instruction ->
                    trail.walk(instruction.first, instruction.second)
                }
            }
            .fold(setOf<Point>()) { acc, trail -> if (acc.isEmpty()) trail.toSet() else acc.intersect(trail) }
            .map { it.distance() }
            .sorted()
            .drop(1)
            .first()
    }

    override fun part2(input: Sequence<String>): Any {
        return "no result"
    }

    private fun Point.distance() = first.absoluteValue + second.absoluteValue

    private fun String.asDirections(): List<Pair<Direction, Int>> =
        split(",")
            .map {
                Direction.valueOf(it.take(1)) to it.drop(1).toInt()
            }

    private tailrec fun MutableList<Point>.walk(
        direction: Direction,
        length: Int
    ): MutableList<Point> {
        if (length == 0) return this

        val currentLocation = last()
        val nextLocation = when (direction) {
            L -> Point(currentLocation.first - 1, currentLocation.second)
            R -> Point(currentLocation.first + 1, currentLocation.second)
            D -> Point(currentLocation.first, currentLocation.second - 1)
            U -> Point(currentLocation.first, currentLocation.second + 1)
        }
        add(nextLocation)
        return walk(direction, length - 1)
    }
}

private enum class Direction {
    L, R, U, D
}

fun main() = Day3().solve()
