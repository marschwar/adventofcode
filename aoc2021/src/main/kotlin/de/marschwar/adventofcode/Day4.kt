package de.marschwar.adventofcode

internal class Day4 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val numbers = createNumbers(input)
        val boards = createBoards(input)
        numbers.forEach { drawnNumber ->
            boards.forEach { board ->
                if (board.markNumber(drawnNumber) && board.winner) {
                    return board.sumOfUnmarkedNumbers * drawnNumber
                }
            }
        }
        error("no result")
    }

    override fun part2(input: Sequence<String>): Any {
        val numbers = createNumbers(input)
        val boards = createBoards(input).toMutableList()
        numbers.forEach { drawnNumber ->
            boards.forEach { board -> board.markNumber(drawnNumber) }
            val winners = boards.filter(Board::winner)
            if (winners.isNotEmpty()) {
                boards.removeAll(winners)
                if (boards.isEmpty()) {
                    return winners.first().sumOfUnmarkedNumbers * drawnNumber
                }
            }
        }
        error("no result")
    }

    private fun createNumbers(input: Sequence<String>): List<Int> {
        return input.first().split(",").map(String::toInt)
    }

    private fun createBoards(input: Sequence<String>): List<Board> {
        return input.drop(1)
            .filter { it.isNotBlank() }
            .chunked(BOARD_SIZE)
            .map(Board::create)
            .toList()
    }

    private data class Board(private val numbers: MutableList<Int?>) {
        val winner: Boolean
            get() = hasWinningRow() || hasWinningColumn()
        val sumOfUnmarkedNumbers: Int
            get() = numbers.filterNotNull().sum()

        fun markNumber(number: Int): Boolean {
            if (numbers.contains(number)) {
                numbers[numbers.indexOf(number)] = null
                return true
            }
            return false
        }

        private fun hasWinningRow(): Boolean = numbers.chunked(BOARD_SIZE).any { row -> row.all { it == null } }
        private fun hasWinningColumn(): Boolean {
            val verticalIndexes = (0 until BOARD_SIZE).map { (it until BOARD_SIZE * BOARD_SIZE step BOARD_SIZE) }
            return verticalIndexes.any { indexRange ->
                numbers.slice(indexRange).all { it == null }
            }
        }

        companion object {
            fun create(rows: List<String>): Board {
                val numbers: List<Int?> = rows
                    .flatMap { it.split(" ") }
                    .filter(String::isNotBlank)
                    .map(String::toInt)
                check(numbers.size == BOARD_SIZE * BOARD_SIZE) { "$rows is invalid" }
                return Board(numbers.toMutableList())
            }
        }
    }

    companion object {
        const val BOARD_SIZE = 5
    }
}

fun main() = Day4().solve()
