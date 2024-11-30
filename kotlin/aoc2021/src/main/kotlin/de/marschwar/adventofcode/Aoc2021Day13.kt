package de.marschwar.adventofcode

internal class Aoc2021Day13 : Puzzle() {

    override fun part1(input: PuzzleInput): Any {
        val instruction = input.first { it.startsWith("fold") }.let { Instruction.of(it) }

        return input.takeWhile { it.isNotBlank() && !it.startsWith("fold") }
            .map(Dot::of)
            .map { instruction.apply(it) }
            .distinct()
            .count()
    }

    override fun part2(input: PuzzleInput): Any {
        val dots = input.takeWhile { it.isNotBlank() && !it.startsWith("fold") }
            .map(Dot::of)
            .toList()
        val finalDots = input.filter { it.startsWith("fold") }
            .map { Instruction.of(it) }
            .fold(dots) { acc, instruction ->
                acc.map { instruction.apply(it) }.distinct()
            }.groupBy { it.y }

        (0..finalDots.keys.maxOf { it }).forEach { dotsInLine ->
            printDots(finalDots.getValue(dotsInLine))
            println()
        }

        return "See above"
    }

    private fun printDots(dots: List<Dot>) {
        var prevPosition = -1
        dots.sortedBy { it.x }
            .forEach { dot ->
                val times = dot.x - prevPosition - 1
                repeat(times) {
                    print(" ")
                }
                print("#")
                prevPosition = dot.x
            }
    }

    private data class Dot(val x: Int, val y: Int) {
        fun foldUpAlongY(foldLine: Int): Dot {
            return if (y < foldLine) this else Dot(x = x, y = foldLine - (y - foldLine))
        }

        fun foldLeftAlongX(foldLine: Int): Dot {
            return if (x < foldLine) this else Dot(x = foldLine - (x - foldLine), y = y)
        }

        companion object {
            fun of(line: String): Dot {
                val split = line.split(",")
                return Dot(split[0].toInt(), split[1].toInt())
            }
        }
    }

    private sealed class Instruction(val foldLine: Int) {
        abstract fun apply(dot: Dot): Dot

        companion object {
            val regex = """fold along (x|y)=(\d+)""".toRegex()
            fun of(line: String): Instruction {
                val (direction, foldline) = regex.matchEntire(line)?.destructured ?: error("no match")
                return when (direction) {
                    "x" -> FoldLeft(foldline.toInt())
                    "y" -> FoldUp(foldline.toInt())
                    else -> error("unknown direction")
                }
            }
        }
    }

    private class FoldUp(foldLine: Int) : Instruction(foldLine) {
        override fun apply(dot: Dot): Dot = dot.foldUpAlongY(foldLine)
    }

    private class FoldLeft(foldLine: Int) : Instruction(foldLine) {
        override fun apply(dot: Dot): Dot = dot.foldLeftAlongX(foldLine)
    }
}

fun main() = Aoc2021Day13().solve()
