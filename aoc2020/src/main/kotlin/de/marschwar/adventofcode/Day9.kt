package de.marschwar.adventofcode

class Day9(private val preambleLength: Int = 25) : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return calcPart1(input.toNumbers())
    }

    private fun calcPart1(allNumbers: List<Long>): Long {
        for (i in 0 until allNumbers.size - preambleLength) {
            val numbersOfWindow = allNumbers.subList(i, preambleLength + i).toSet()
            val targetNumber = allNumbers[i + preambleLength]
            if (!numbersOfWindow.canSumTo(targetNumber)) {
                return targetNumber
            }
        }
        error("no result")
    }

    override fun part2(input: Sequence<String>): Any {
        val allNumbers = input.toNumbers()
        val target = calcPart1(allNumbers)
        for (i in allNumbers.indices) {
            if (allNumbers[i] == target) {
                continue
            }
            var idx = i
            var sum = allNumbers[idx]
            while (sum < target) {
                sum += allNumbers[++idx]
            }
            if (sum == target) {
                val resultNumbers = allNumbers.subList(i, idx)
                return resultNumbers.minOrNull()!! + resultNumbers.maxOrNull()!!
            }
        }

        error("no result")
    }

    private fun Sequence<String>.toNumbers() = map { it.toLong() }.toList()

    private fun Set<Long>.canSumTo(target: Long): Boolean = any { first ->
        val delta = target - first
        delta != first && this.contains(delta)
    }
}

fun main() = Day9().solve()
