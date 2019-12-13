package de.marschwar.adventofcode.v2019

class IntCodeComputer(input: String) {
    private val program: Map<Long, Long> = input
        .split(",")
        .mapIndexed { index, s -> index.toLong() to s.toLong() }
        .toMap()

    fun run(vararg inputs: Long): Long {
        return program.toMutableMap().doRun(NeverEndingStack(inputs.toTypedArray()))
    }

    private tailrec fun MutableMap<Long, Long>.doRun(
        inputs: NeverEndingStack<Long>,
        idx: Long = 0,
        rel: Long = 0,
        output: Long = 0
    ): Long {

        val instruction = getValue(idx)
        if (instruction == 99L) {
            println()
            return output
        }
        var nextOutput = output
        var nextRelativeBase = rel

        val opCode = OpCode.from(instruction.toInt() % 100)

        val modes = instruction.toModes()

        val firstParamValue = getValue(modes, idx, 1, rel)
        val secondParamValue = getValue(modes, idx, 2, rel)

        var nextIndex = idx
        when (opCode) {
            OpCode.ADD ->
                setValue(modes, idx, 3, rel, firstParamValue + secondParamValue)
                    .also { nextIndex += 4 }
            OpCode.MULTIPLY ->
                setValue(modes, idx, 3, rel, firstParamValue * secondParamValue)
                    .also { nextIndex += 4 }
            OpCode.SET ->
                setValue(modes, idx, 3, rel, inputs.get()).also { nextIndex += 2 }
            OpCode.PRINT -> {
                nextOutput = firstParamValue
                nextIndex += 2
            }
            OpCode.ADJUST_RELATIVE_BASE -> {
                nextRelativeBase = rel + firstParamValue
                nextIndex += 2
            }
            OpCode.LESS_THAN ->
                setValue(modes, idx, 3, rel, if (firstParamValue < secondParamValue) 1L else 0L)
                    .also { nextIndex += 4 }
            OpCode.EQUAL ->
                setValue(modes, idx, 3, rel, if (firstParamValue == secondParamValue) 1L else 0L)
                    .also { nextIndex += 4 }

            OpCode.JUMP_IF_TRUE ->
                nextIndex = if (firstParamValue != 0L) secondParamValue else idx + 3
            OpCode.JUMP_IF_FALSE ->
                nextIndex = if (firstParamValue == 0L) secondParamValue else idx + 3
        }

        return doRun(inputs = inputs, idx = nextIndex, output = nextOutput, rel = nextRelativeBase)
    }

    private fun MutableMap<Long, Long>.addr(modes: List<Mode>, currentIndex: Long, offset: Int, relativeBase: Long):
            Long {
        val mode = modes[offset - 1]
        val index = currentIndex + offset
        return when (mode) {
            Mode.POSITION -> getOrDefault(index, DEFAULT_VALUE)
            Mode.VALUE -> index
            Mode.RELATIVE -> relativeBase + getOrDefault(index, DEFAULT_VALUE)
        }
    }

    private fun MutableMap<Long, Long>.setValue(
        modes: List<Mode>,
        currentIndex: Long,
        offset: Int,
        relativeBase: Long,
        value: Long
    ) {
        set(addr(modes, currentIndex, offset, relativeBase), value)
    }

    private fun MutableMap<Long, Long>.getValue(
        modes: List<Mode>,
        currentIndex: Long,
        offset: Int,
        relativeBase: Long
    ): Long = getOrDefault(addr(modes, currentIndex, offset, relativeBase), DEFAULT_VALUE)


    private fun MutableMap<Long, Long>.calculateParamValue(
        mode: Mode,
        param: Long,
        relativeBase: Long
    ): Long {
        return when (mode) {
            Mode.POSITION -> getOrDefault(param, DEFAULT_VALUE)
            Mode.VALUE -> param
            Mode.RELATIVE -> getOrDefault(relativeBase + param, DEFAULT_VALUE)
        }
    }

    private enum class OpCode(val value: Int, val supportsMultipleParameters: Boolean = true) {

        ADD(1),
        MULTIPLY(2),
        SET(3, false),
        PRINT(4, false),
        JUMP_IF_TRUE(5),
        JUMP_IF_FALSE(6),
        LESS_THAN(7),
        EQUAL(8),
        ADJUST_RELATIVE_BASE(9, false);

        companion object {
            fun from(value: Int): OpCode =
                values().find { it.value == value } ?: error("invalid op code $value")
        }
    }

    private enum class Mode {
        POSITION, VALUE, RELATIVE
    }

    private fun Long.toModes(): List<Mode> {
        return toString()
            .dropLast(2)
            .padStart(4, '0')
            .map {
                when (it) {
                    '0' -> Mode.POSITION
                    '1' -> Mode.VALUE
                    '2' -> Mode.RELATIVE
                    else -> error("unknown mode $it")
                }
            }.reversed()
    }

    private class NeverEndingStack<T>(items: Array<T>) {
        val items: MutableList<T> = items.toMutableList()

        fun get(): T {
            return if (items.size == 1) items.get(0) else items.removeAt(0)
        }
    }

    companion object {
        private const val DEFAULT_VALUE = 0L
    }
}