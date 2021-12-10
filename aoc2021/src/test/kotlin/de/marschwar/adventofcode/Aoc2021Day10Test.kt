package de.marschwar.adventofcode

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class Aoc2021Day10Test {

    val puzzle = Aoc2021Day10()
    val inputs = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent()
        .lineSequence()

    @Test
    fun part1() {

        val result = puzzle.part1(inputs)

        assertThat(result).isEqualTo(26397)
    }

    @Test
    fun part2() {

        val result = puzzle.part2(inputs)

        assertThat(result).isEqualTo(288957)
    }
}
