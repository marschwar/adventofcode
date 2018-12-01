package de.codekenner.aoc2018

class Day1 : Puzzle() {
    override fun part1(input: Sequence<String>): Any =
        input
            .map(String::toInt)
            .sum()

    override fun part2(input: Sequence<String>): Any =
        withState(input.repeat().map(String::toInt))
            .first { it.duplicate }
            .frequency

    private fun withState(numbers: Sequence<Int>): Sequence<State> =
        sequence {
            var current = State(false, 0, mutableSetOf(0))
            for (number in numbers) {
                current = current.next(number)
                yield(current)
            }
        }
}

private fun <T> Sequence<T>.repeat(): Sequence<T> = sequence {
    while (true) yieldAll(this@repeat)
}

private data class State(val duplicate: Boolean, val frequency: Int, val knownFrequencies: MutableSet<Int>) {
    fun next(number: Int): State {
        val newFrequency = frequency + number
        val duplicate = knownFrequencies.contains(newFrequency)
        knownFrequencies.add(newFrequency)
        return State(duplicate, newFrequency, knownFrequencies)
    }
}

fun main() = Day1().solve()
