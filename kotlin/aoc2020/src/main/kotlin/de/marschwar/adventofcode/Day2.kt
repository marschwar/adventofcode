package de.marschwar.adventofcode

class Day2 : Puzzle() {
    val regex = """(\d+)-(\d+)\s(\w):\s(\w+)""".toRegex()

    override fun part1(input: Sequence<String>): Any {
        return input.map { line ->
            val groups = regex.matchEntire(line)!!.groupValues
            PasswordRuleCheck1(
                range = groups[1].toInt()..groups[2].toInt(),
                char = groups[3].first(),
                password = groups[4]
            )
        }.count { it.valid }
    }

    override fun part2(input: Sequence<String>): Any {
        return input.map { line ->
            val groups = regex.matchEntire(line)!!.groupValues
            PasswordRuleCheck2(
                firstIndex = groups[1].toInt(),
                secondIndex = groups[2].toInt(),
                char = groups[3].first(),
                password = groups[4]
            )
        }.count { it.valid }
    }

    private data class PasswordRuleCheck1(val range: IntRange, val char: Char, val password: String) {
        val valid: Boolean
            get() = password.count { it == char } in range
    }

    private data class PasswordRuleCheck2(
        val firstIndex: Int,
        val secondIndex: Int,
        val char: Char,
        val password: String
    ) {
        val valid: Boolean
            get() = (password[firstIndex - 1] == char)
                .xor(password[secondIndex - 1] == char)
    }
}

fun main() = Day2().solve()
