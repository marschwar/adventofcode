package de.marschwar.adventofcode

import kotlin.math.pow

class Day10 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val numbers = input.map(String::toInt).sorted().toList()
        val diffs = numbers.foldIndexed(IntArray(4)) { index, acc, item ->
            val prev = if (index == 0) 0 else numbers[index - 1]
            val diff = item - prev
            acc[diff] = acc[diff] + 1
            acc
        }
        return diffs[1] * (diffs[3] + 1)
    }

    override fun part2(input: Sequence<String>): Any {
        val numbers = input.map(String::toInt).sorted().toMutableList()
        numbers.add(0, 0)
        numbers.add(numbers.last() + 3)
        val (histogram, _) = numbers.foldIndexed(IntArray(6) to 0) { index, acc, i ->
            if (index == numbers.size - 1) {
                acc
            } else {
                var (hist, length) = acc
                if (numbers[index + 1] == i + 1) {
                    length++
                } else {
                    hist[length + 1]++
                    length = 0
                }
                hist to length
            }
        }
        return 2f.pow(histogram[3]).toLong() * 4f.pow(histogram[4]).toLong() * 7f.pow(histogram[5]).toLong()
    }
}

fun main() = Day10().solve()
