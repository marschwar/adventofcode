package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Direction
import de.marschwar.adventofcode.Direction.E
import de.marschwar.adventofcode.Direction.N
import de.marschwar.adventofcode.Direction.S
import de.marschwar.adventofcode.Direction.W
import de.marschwar.adventofcode.Point
import de.marschwar.adventofcode.Puzzle
import de.marschwar.adventofcode.PuzzleInput
import java.util.function.Consumer
import java.util.function.Supplier

class Day11 : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        val computer = IntCodeComputer(input.first())
        val consumer = CollectingConsumer()
        computer.run(Supplier { 0L }, consumer)

        val visited = mutableSetOf<Point>()
        var currentPosition = Point(0, 0)
        var currentDirection = N

        consumer.result.chunked(2).forEach { (_, turn) ->
            visited.add(currentPosition)
            currentDirection = if (turn == 0L) currentDirection.turnLeft() else currentDirection.turnRight()
            currentPosition = move(currentPosition, currentDirection)
        }

        return visited.size
    }

    override fun part2(input: PuzzleInput): Any {
        return "no result"
    }

    private fun Direction.turnLeft(): Direction = when (this) {
        N -> W
        E -> N
        S -> E
        W -> S
    }

    private fun Direction.turnRight(): Direction = when (this) {
        N -> E
        E -> S
        S -> W
        W -> N
    }

    private fun move(position: Point, direction: Direction): Point {
        return when (direction) {
            N -> Point(position.first, position.second - 1)
            E -> Point(position.first + 1, position.second)
            S -> Point(position.first, position.second + 1)
            W -> Point(position.first - 1, position.second)
        }
    }

    private class CollectingConsumer : Consumer<Long> {
        private val values = mutableListOf<Long>()
        val result: List<Long>
            get() = values

        override fun accept(value: Long) {
            values.add(value)
        }
    }
}

fun main() {
    Day11().solve()
}