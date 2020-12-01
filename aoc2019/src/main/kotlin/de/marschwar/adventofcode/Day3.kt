package de.marschwar.adventofcode

import de.marschwar.adventofcode.LRUDDirection.D
import de.marschwar.adventofcode.LRUDDirection.L
import de.marschwar.adventofcode.LRUDDirection.R
import de.marschwar.adventofcode.LRUDDirection.U
import de.marschwar.adventofcode.LRUDDirection.valueOf
import kotlin.math.absoluteValue

class Day3() : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val trails = convertToTrails(input)
        val crossings = findCrossings(trails)

        return crossings
            .map { it.distance() }
            .sorted()
            .drop(1)
            .first()
    }

    override fun part2(input: Sequence<String>): Any {
        val trails: List<List<Point>> = convertToTrails(input)
        val crossings: List<Point> = findCrossings(trails)

        return crossings
            .map { point ->
                trails.sumBy { trail ->
                    trail.indexOf(point)
                }
            }
            .sorted()
            .drop(1)
            .first()
    }

    private fun findCrossings(trails: List<List<Point>>): List<Point> {
        return trails
            .fold(setOf<Point>()) { acc, trail -> if (acc.isEmpty()) trail.toSet() else acc.intersect(trail) }
            .toList()
    }

    private fun convertToTrails(input: Sequence<String>): List<List<Point>> {
        return input
            .map { it.asDirections() }
            .map { instructions ->
                instructions.fold(mutableListOf(Point(0, 0))) { trail, instruction ->
                    trail.walk(instruction.first, instruction.second)
                }
            }
            .toList()
    }

    private fun Point.distance() = first.absoluteValue + second.absoluteValue

    private fun String.asDirections(): List<Pair<LRUDDirection, Int>> =
        split(",")
            .map {
                valueOf(it.take(1)) to it.drop(1).toInt()
            }

    private tailrec fun MutableList<Point>.walk(
        direction: LRUDDirection,
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

private enum class LRUDDirection {
    L, R, U, D
}

fun main() = Day3().solve()
