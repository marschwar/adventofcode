package de.codekenner.aoc2018

class Day3 : Puzzle() {
    override fun part1(input: Sequence<String>): Any {
        return input
            .map { Claim.of(it) }
            .flatMap { it.fabricPositions.asSequence() }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }
    }

    override fun part2(input: Sequence<String>): Any {
        val claims = input.map { Claim.of(it) }.toList()
        return claims.filterNot { it.intersects(claims) }.first().number
    }

}

private class Claim(val number: Int, left: Int, top: Int, width: Int, height: Int) {
    val fabricPositions: Set<Point> = (left..(left + width - 1)).flatMap { x ->
        (top..(top + height - 1)).map { y -> x to y }
    }.toSet()

    fun intersects(others: Collection<Claim>): Boolean =
        others.any { other ->
            number != other.number && fabricPositions.any { it in other.fabricPositions }
        }

    companion object {
        private val regex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()
        fun of(line: String): Claim {
            val v = regex.matchEntire(line)?.groupValues ?: throw IllegalArgumentException("$line does not match")
            val number = v[1].toInt()
            val left = v[2].toInt()
            val top = v[3].toInt()
            val width = v[4].toInt()
            val height = v[5].toInt()
            return Claim(number, left, top, width, height)
        }
    }

}

fun main() = Day3().solve()
