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
        val trails = convertToTrailsSequence(input)
        val crossings = findCrossings(trails)

        return crossings
            .map { it.distance() }
            .sorted()
            .drop(1)
            .first()
    }

    override fun part2(input: Sequence<String>): Any {
        val trails: List<List<Point>> = convertToTrailsSequence(input)
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

    private fun convertToTrailsSequence(input: Sequence<String>): List<List<Point>> {
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
