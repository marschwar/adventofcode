package de.marschwar.adventofcode

import kotlin.math.abs

class Day12(private val steps: Int = 1000) : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        val moons = input.toMoons()
        return simulate(moons, steps).map {
            it.position.sum * it.velocity.sum
        }.sum()
    }

    override fun part2(input: PuzzleInput): Any {
        val moons = input.toMoons()
        val rows = listOf(Vector::x, Vector::y, Vector::z)
        val cycles = rows.map { row ->
            val initialData = moons.map { moon ->
                row(moon.position) to row(moon.velocity)
            }
            simulateUntilDuplicate(initialData, row, moons)
        }
        return lcm(lcm(cycles[0], cycles[1]), cycles[2])
    }

    private tailrec fun simulate(moons: List<Moon>, stepsLeft: Int): List<Moon> {
        if (stepsLeft == 0) return moons
        val newMoons = simulate(moons)
        return simulate(newMoons, stepsLeft - 1)
    }

    private tailrec fun simulateUntilDuplicate(
        initialData: List<Pair<Int, Int>>,
        row: (Vector) -> Int,
        moons: List<Moon>,
        cycleCount: Long = 0
    ): Long {
        val data = moons.map { moon ->
            row(moon.position) to row(moon.velocity)
        }
        if (cycleCount > 0 && data == initialData) return cycleCount

        val newMoons = simulate(moons)

        return simulateUntilDuplicate(initialData, row, newMoons, cycleCount + 1)
    }

    private fun simulate(moons: List<Moon>): List<Moon> {
        return moons.map { moon ->
            val velocityChange = moons.minus(moon).fold(Vector()) { acc, otherMoon ->
                acc + calculateVelocityChange(moon.position, otherMoon.position)
            }
            Moon(
                position = moon.position + moon.velocity + velocityChange,
                velocity = moon.velocity + velocityChange
            )
        }
    }

    private fun PuzzleInput.toMoons(): List<Moon> {
        val regex = Regex("""<x=([-]?\d+), y=([-]?\d+), z=([-]?\d+)>""")
        return map { regex.matchEntire(it) }
            .filterNotNull()
            .map { it.groups }
            .map { Vector(x = it[1]!!.value.toInt(), y = it[2]!!.value.toInt(), z = it[3]!!.value.toInt()) }
            .map { Moon(position = it) }
            .toList()
    }

    private fun calculateVelocityChange(position1: Vector, position2: Vector): Vector =
        Vector(
            x = calculateChange(position1.x, position2.x),
            y = calculateChange(position1.y, position2.y),
            z = calculateChange(position1.z, position2.z)
        )

    private fun calculateChange(i1: Int, i2: Int): Int {
        val delta = i2 - i1
        return when {
            delta == 0 -> 0
            delta < 0 -> -1
            delta > 0 -> 1
            else -> error("not happening")
        }
    }

    private data class Moon(val position: Vector, val velocity: Vector = Vector())

    private data class Vector(val x: Int = 0, val y: Int = 0, val z: Int = 0) {
        val sum: Int
            get() = abs(x) + abs(y) + abs(z)

        operator fun plus(other: Vector): Vector = Vector(
            x = x + other.x,
            y = y + other.y,
            z = z + other.z
        )

        override fun toString(): String {
            return "$x/$y/$z)"
        }

    }
}

fun main() {
    Day12(1000).solve()
}