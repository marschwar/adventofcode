package de.marschwar.adventofcode

import kotlin.system.measureTimeMillis

abstract class Puzzle {

    private val input: Sequence<String>

    init {
        input = readInput()
    }

    private fun readInput(): Sequence<String> {
        val clazz = this.javaClass
        val year = clazz.packageName.split(".").last()
        val rawInput = clazz.getResource("/input/$year/${clazz.simpleName}.txt").readText()
        return rawInput
            .lineSequence()
            .map(String::trim)
            .filterNot(String::isEmpty)
    }

    fun solve() {
        val duration1 = measureTimeMillis { println("Part 1: ${part1(input)}") }
        println("It took ${duration1}ms")
        val duration2 = measureTimeMillis { println("Part 2: ${part2(input)}") }
        println("It took ${duration2}ms")
    }

    abstract fun part1(input: Sequence<String>): Any

    abstract fun part2(input: Sequence<String>): Any

}