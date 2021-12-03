package de.marschwar.adventofcode

import kotlin.math.pow

internal class Day3 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val length = input.elementAt(0).length
        return input
            .fold(Collector(length)) { acc, s -> acc.apply(s) }
            .calculateResult()
    }

    override fun part2(input: Sequence<String>): Any {
        TODO()
    }

    private class Collector(length: Int) {
        private var countersOfOnes = FloatArray(length) { 0f }
        private var total = 0f

        private val gammaRate: Int
            get() {
                val half: Float = total.div(2)
                return countersOfOnes
                    .mapIndexed { index, i -> if (i < half) 0 else 2f.pow(countersOfOnes.size - 1 - index).toInt() }
                    .sum()
            }

        private val epsilonRate: Int
            get() {
                val half: Float = total.div(2)
                return countersOfOnes
                    .mapIndexed { index, i -> if (i > half) 0 else 2f.pow(countersOfOnes.size - 1 - index).toInt() }
                    .sum()
            }

        fun apply(number: String): Collector {

            number.forEachIndexed { index, c -> apply(index, c) }
            total++
            return this
        }

        private fun apply(index: Int, c: Char) {
            if (c == '1') {
                countersOfOnes[index] = countersOfOnes[index] + 1
            }
        }

        fun calculateResult(): Int {
            return gammaRate * epsilonRate
        }
    }
}

fun main() = Day3().solve()
