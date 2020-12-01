package de.marschwar.adventofcode

class Day8(private val imageWidth: Int, private val imageHeight: Int) : Puzzle() {
    override fun part1(input: PuzzleInput): Any {
        val layerStats: List<Map<Char, List<Char>>> =
            input.first().chunked(imageWidth * imageHeight).map { layer -> layer.groupBy { it } }
        val layerWithLeastZeros = layerStats.minBy { stat -> stat['0']?.count() ?: 0 }!!
        return (layerWithLeastZeros['1']?.count() ?: 0) * (layerWithLeastZeros['2']?.count() ?: 0)
    }

    override fun part2(input: PuzzleInput): Any {
        val decoded = SpaceImageDecoder(imageWidth, imageHeight).decode(input.first())
        return "\n" + decoded.reduce { acc, layer -> acc + layer }.rows.map { row ->
            row.map { if (it == '1') "\u25a0" else " " }.joinToString(separator = "")
        }.joinToString(separator = "\n")
    }

    class SpaceImageDecoder(private val imageWidth: Int, private val imageHeight: Int) {
        fun decode(input: String): List<Layer> {
            return input.chunked(imageWidth * imageHeight).map { chunk ->
                Layer(chunk.chunked(imageWidth).map { it.toList() })
            }
        }
    }

    data class Layer(val rows: List<List<Char>>) {
        operator fun plus(other: Layer): Layer =
            Layer(rows.zip(other.rows).map { (leftRow, rightRow) ->
                leftRow.zip(rightRow).map { (leftChar, rightChar) ->
                    if (leftChar == '2') rightChar else leftChar
                }
            })
    }
}

fun main() {
    Day8(25, 6).solve()
}