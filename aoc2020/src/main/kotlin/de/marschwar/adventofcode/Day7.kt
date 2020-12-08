package de.marschwar.adventofcode

class Day7 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return input.toColorMap().getValue("shiny gold").collectContainers().size
    }

    override fun part2(input: Sequence<String>): Any {
        return input.toColorMap().getValue("shiny gold").sumContent() - 1
    }

    private fun Sequence<String>.toColorMap(): Map<String, Color> {
        val colors = mutableMapOf<String, Color>()
        forEach { line ->
            val name = line.substringBefore("bags").trim()
            val outerColor = colors.computeIfAbsent(name) { Color(name) }

            val instructionParts = line.substringAfter("contain").split(',')
            instructionParts
                .filterNot { it.contains("no other bags") }
                .map { Instruction.of(it) }
                .forEach { instruction ->
                    val innerColor = colors.computeIfAbsent(instruction.colorName) { Color(instruction.colorName) }
                    innerColor.addContainer(outerColor, instruction.count)
                    outerColor.addContent(innerColor, instruction.count)
                }
        }
        return colors
    }


    private data class Instruction(val colorName: String, val count: Int) {
        companion object {
            private val regex = """\s*(\d+) (\w+ \w+) bag.*""".toRegex()
            fun of(part: String): Instruction {
                val (count, name) = regex.matchEntire(part)?.destructured ?: error("'$part' not matching")
                return Instruction(
                    count = count.toInt(),
                    colorName = name
                )
            }
        }
    }

    private data class Color(val name: String) {
        private val containedBy = mutableSetOf<Rule>()
        private val containing = mutableSetOf<Rule>()

        fun addContainer(color: Color, count: Int) {
            containedBy.add(Rule(color, count))
        }

        fun addContent(color: Color, count: Int) {
            containing.add(Rule(color, count))
        }

        fun collectContainers(names: Set<String> = emptySet()): Set<String> {
            return containedBy.fold(names) { acc, rule ->
                if (acc.contains(rule.color.name))
                    acc
                else
                    rule.color.collectContainers(acc + rule.color.name)
            }
        }

        fun sumContent(): Int {
            return 1 + containing.sumBy { it.count * it.color.sumContent() }
        }
    }

    private data class Rule(val color: Color, val count: Int)
}

fun main() = Day7().solve()
