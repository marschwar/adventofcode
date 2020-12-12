package de.marschwar.adventofcode

import kotlin.math.abs

class Day12() : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val ship = Ship()

        input.forEach { line ->
            val op = line[0]
            val amount = line.drop(1).toInt()
            when (op) {
                'F' -> ship.forward(amount)
                'R' -> ship.turnRight(amount)
                'L' -> ship.turnLeft(amount)
                'N', 'S', 'E', 'W' -> ship.move(Direction.valueOf(op.toString()), amount)
                else -> error("invalid op $op")
            }
        }

        return ship.traveledDistance
    }

    override fun part2(input: Sequence<String>): Any {
        val ship = Ship()

        input.forEach { line ->
            val op = line[0]
            val amount = line.drop(1).toInt()
            when (op) {
                'F' -> ship.forwardToWaypoint(amount)
                'R' -> ship.turnRightWaypoint(amount)
                'L' -> ship.turnLeftWaypoint(amount)
                'N', 'S', 'E', 'W' -> ship.moveWaypoint(Direction.valueOf(op.toString()), amount)
                else -> error("invalid op $op")
            }
        }

        return ship.traveledDistance
    }

    private class Ship {
        private var direction: Direction = Direction.E
        private val steps = IntArray(4)
        private var waypointEast = 10
        private var waypointNorth = 1

        val traveledDistance: Int
            get() = abs(steps[0] - steps[2]) + abs(steps[1] - steps[3])

        fun turnRightWaypoint(degrees: Int) {
            val east = waypointEast
            val north = waypointNorth

            when (degrees) {
                90 -> {
                    waypointEast = north
                    waypointNorth = -east
                }
                180 -> {
                    waypointEast = -east
                    waypointNorth = -north
                }
                270 -> {
                    waypointEast = -north
                    waypointNorth = east
                }
                else -> error("$degrees degrees")
            }
        }

        fun turnLeftWaypoint(degrees: Int) = turnRightWaypoint(360 - degrees)

        fun turnRight(degrees: Int) {
            direction = direction.turnRight(degrees)
        }

        fun turnLeft(amount: Int) = turnRight(-1 * amount)

        fun forward(steps: Int) {
            move(this.direction, steps)
        }

        fun forwardToWaypoint(amount: Int) {
            move(Direction.E, waypointEast * amount)
            move(Direction.N, waypointNorth * amount)
        }

        fun moveWaypoint(direction: Direction, amount: Int) {
            when (direction) {
                Direction.E -> waypointEast += amount
                Direction.S -> waypointNorth -= amount
                Direction.W -> waypointEast -= amount
                Direction.N -> waypointNorth += amount
            }
        }

        fun move(direction: Direction, steps: Int) {
            this.steps[direction.ordinal] += steps
        }
    }

    private enum class Direction {
        E, S, W, N;

        fun turnRight(degrees: Int): Direction {
            val newDirectionIdx = Math.floorMod(ordinal + (degrees / 90), values().size)
            return values()[newDirectionIdx]
        }
    }
}

fun main() = Day12().solve()
