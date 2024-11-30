package de.marschwar.adventofcode

internal class Aoc2021Day17 : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        val targetArea = toTargetArea(input)
        return getHitSequences(targetArea)
            .maxOf { seq ->
                seq.takeWhile { targetArea.getState(it) is Approaching }
                    .maxOf { it.second }
            }
    }

    override fun part2(input: PuzzleInput): Any {
        val targetArea = toTargetArea(input)
        return getHitSequences(targetArea).count()
    }

    private fun toTargetArea(input: PuzzleInput): TargetArea {
        val (x1, x2, y1, y2) = INPUT_REGEX.matchEntire(input.first())?.destructured ?: error("no match")
        return TargetArea(x1.toInt(), x2.toInt(), y1.toInt(), y2.toInt())
    }

    private fun getHitSequences(targetArea: TargetArea) =
        (1..targetArea.x2).flatMap { x ->
            val upperBound = ((1..x).sum() - targetArea.y2)
            (targetArea.y1..upperBound).map { y -> Point(x, y) }
        }
            .map { createPositions(it.first, it.second) }
            .filter { seq ->
                seq.map { targetArea.getState(it) }
                    .dropWhile { it is Approaching }
                    .first() is Hit
            }

    private data class TargetArea(val x1: Int, val x2: Int, val y1: Int, val y2: Int) {
        fun getState(point: Point): State = when {
            isHit(point) -> Hit(point)
            isMiss(point) -> Miss(point)
            else -> Approaching(point)
        }

        private fun isHit(point: Point): Boolean = point.first in x1..x2 && point.second in y1..y2
        private fun isMiss(point: Point): Boolean = point.first > x2 || point.second < y1
    }

    private sealed class State(val position: Point) {
        override fun toString(): String {
            return "${javaClass.simpleName}[$position]"
        }
    }

    private class Approaching(position: Point) : State(position)
    private class Hit(position: Point) : State(position)
    private class Miss(position: Point) : State(position)

    private fun createPositions(velocityX: Int, velocityY: Int): Sequence<Point> {
        return sequence {
            var currentPosition = Point(0, 0)
            var currentX = velocityX
            var currentY = velocityY
            while (true) {
                yield(currentPosition)
                currentPosition = Point(currentPosition.first + currentX, currentPosition.second + currentY)
                currentX = when {
                    currentX > 0 -> currentX - 1
                    currentX < 0 -> currentX + 1
                    else -> 0
                }
                currentY -= 1
            }
        }
    }

    companion object {
        private val INPUT_REGEX = """target area: x=([-]?\d+)\.{2}([-]?\d+), y=([-]?\d+)\.{2}([-]?\d+)""".toRegex()
    }
}

fun main() = Aoc2021Day17().solve()
