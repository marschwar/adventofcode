package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Puzzle

class Day1 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return input
            .map { it.toInt() }
            .sumBy { it.massToFuel() }
    }

    override fun part2(input: Sequence<String>): Any {
        return input
            .map { it.toInt() }
            .sumBy { massToFuelIncludeFuelWeight(it) }
    }

    private fun Int.massToFuel() = this.div(3).minus(2)

    tailrec fun massToFuelIncludeFuelWeight(value: Int, acc: Int = 0): Int =
        if (value <= 0)
            acc
        else {
            val fuelWeight = Math.max(0, value.div(3).minus(2))
            massToFuelIncludeFuelWeight(fuelWeight, acc + fuelWeight)
        }


}


fun main() = Day1().solve()
