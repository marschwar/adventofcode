package de.codekenner.aoc2018

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

abstract class Puzzle {
    fun solve() {
        val clazz = this.javaClass
        val rawInput = clazz.getResource("/input/${clazz.simpleName}.txt").readText()
        val input = rawInput
            .lineSequence()
            .map(String::trim)
            .filterNot(String::isEmpty)
        val duration1 = measureTimeMillis { println("Part 1: ${part1(input)}") }
        println("It took ${duration1}ms")
        val duration2 = measureTimeMillis { println("Part 2: ${part2(input)}") }
        println("It took ${duration2}ms")
    }

    abstract fun part1(input: Sequence<String>): Any

    abstract fun part2(input: Sequence<String>): Any

}