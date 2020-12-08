package de.marschwar.adventofcode

class Day8 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val program = createProgram(input)
        lateinit var state: State
        do {
            state = program.executeNext()
        } while (state == Running)
        return program.acc
    }

    override fun part2(input: Sequence<String>): Any {
        TODO()
    }

    private fun createProgram(input: Sequence<String>): Program {
        val operations = input
            .map { line -> Operation.of(line) }
            .toList()
        return Program(operations)
    }

    private class Program(val operations: List<Operation>) {
        var acc: Int = 0
            private set

        private var currentIndex = 0
        private var state: State = Waiting

        fun executeNext(): State {
            check(state != Finished)
            execute(operations[currentIndex])
            return state
        }

        private fun execute(operation: Operation) {
            if (operation.executed) {
                state = Finished
                return
            }
            when (operation) {
                is Nop -> currentIndex++
                is Jmp -> currentIndex += operation.value
                is Acc -> {
                    acc += operation.value
                    currentIndex++
                }
            }
            operation.executed = true
            state = Running
        }
    }
}

private sealed class Operation {
    var executed: Boolean = false

    companion object {
        private val regex = """(\w+)\s([+-]\d+)""".toRegex()
        fun of(input: String): Operation {
            val (op, value) = regex.matchEntire(input)?.destructured ?: error("'$input' does not match")
            return when (op) {
                "nop" -> Nop()
                "jmp" -> Jmp(value.toInt())
                "acc" -> Acc(value.toInt())
                else -> error("unknown operation '$op'")
            }
        }
    }
}

private data class Acc(val value: Int) : Operation()
private data class Jmp(val value: Int) : Operation()
private class Nop : Operation() {
    override fun toString(): String = "Nop"
}

private sealed class State
private object Waiting : State()
private object Running : State()
private object Finished : State()

fun main() = Day8().solve()
