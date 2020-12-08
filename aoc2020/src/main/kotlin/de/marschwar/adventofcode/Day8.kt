package de.marschwar.adventofcode

class Day8 : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        val program = createProgram(input)
        program.executeAll()
        return program.acc
    }

    override fun part2(input: Sequence<String>): Any {

        val programSequence = sequence {
            val originalProgram = createProgram(input)
            val operations = originalProgram.operations
            var candidateIdx = operations.nextCandidateIndex()
            while (candidateIdx > -1) {
                val newOp = operations[candidateIdx].flipOrSelf()
                val candidateProgram = createProgram(input, candidateIdx to newOp)

                yield(candidateProgram)

                candidateIdx = operations.nextCandidateIndex(candidateIdx + 1)
            }
        }
        val result = programSequence.find { program ->
            program.executeAll() == Finished
        }
        return result?.acc ?: error("no result")
    }

    private fun List<Operation>.nextCandidateIndex(starting: Int = 0): Int {
        if (starting >= size) return -1

        for (i in starting until size) {
            val elem = get(i)
            if (elem is Nop || elem is Jmp) {
                return i
            }
        }
        return -1
    }

    private fun createProgram(input: Sequence<String>, replacement: Pair<Int, Operation>? = null): Program {
        val operations = input
            .mapIndexed { idx, line ->
                if (replacement?.first == idx)
                    replacement.second
                else
                    Operation.of(line)
            }
            .toList()
        return Program(operations)
    }

    private class Program(val operations: List<Operation>) {
        var acc: Int = 0
            private set

        private var currentIndex = 0
        private var state: State = Idle

        fun executeNext(): State {
            check(state in listOf(Idle, Running))

            execute(operations[currentIndex])

            return state
        }

        private fun execute(operation: Operation) {
            if (operation.executed) {
                state = Loop
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
            state = if (currentIndex < operations.size) Running else Finished
        }

        fun executeAll(): State {
            lateinit var state: State
            do {
                state = executeNext()
            } while (state == Running)
            return state
        }
    }
}

private sealed class Operation {
    var executed: Boolean = false

    open fun flipOrSelf(): Operation = this

    companion object {
        private val regex = """(\w+)\s([+-]\d+)""".toRegex()
        fun of(input: String): Operation {
            val (op, value) = regex.matchEntire(input)?.destructured ?: error("'$input' does not match")
            val intValue = value.toInt()
            return when (op) {
                "nop" -> Nop(intValue)
                "jmp" -> Jmp(intValue)
                "acc" -> Acc(intValue)
                else -> error("unknown operation '$op'")
            }
        }
    }
}

private data class Acc(val value: Int) : Operation()
private data class Jmp(val value: Int) : Operation() {
    override fun flipOrSelf(): Operation = Nop(value)
}

private data class Nop(val value: Int) : Operation() {
    override fun flipOrSelf(): Operation = Jmp(value)
}

private sealed class State
private object Idle : State()
private object Running : State()
private object Finished : State()
private object Loop : State()

fun main() = Day8().solve()
