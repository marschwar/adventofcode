package de.marschwar.adventofcode.v2019

class IntCodeComputer(input: String) {
    private val program: List<Int> = input
        .split(",")
        .map(String::toInt)
        .toList()

    fun run(inputSetting: Int): Int {
        return program.toMutableList().doRun(inputSetting) ?: throw IllegalStateException("no result")
    }

    private tailrec fun MutableList<Int>.doRun(inputSetting: Int, currentIndex: Int = 0, output: Int? = null): Int? {
        val instruction = get(currentIndex)
        if (instruction == 99) {
            return output
        }
        val opCode = OpCode.from(instruction % 100)

        val modes = (instruction / 100).toString().padStart(4, '0')

        val firstParam = get(currentIndex + 1)
        val firstParamValue = if (modes[3] == POSITION_MODE) get(firstParam) else firstParam

        val secondParam = get(currentIndex + 2)
        val secondParamValue =
            if (opCode.supportsMultipleParameters && modes[2] == POSITION_MODE)
                get(secondParam)
            else
                secondParam

        val targetIndex = get(currentIndex + 3)
        var newOutput: Int? = null

        when (opCode) {
            OpCode.ADD -> set(targetIndex, firstParamValue + secondParamValue)
            OpCode.MULTIPLY -> set(targetIndex, firstParamValue * secondParamValue)
            OpCode.SET -> set(firstParam, inputSetting)
            OpCode.PRINT -> newOutput = firstParamValue
            OpCode.LESS_THAN -> set(targetIndex, if (firstParamValue < secondParamValue) 1 else 0)
            OpCode.EQUAL -> set(targetIndex, if (firstParamValue == secondParamValue) 1 else 0)

            OpCode.JUMP_IF_TRUE,
            OpCode.JUMP_IF_FALSE -> {
                // NOOP
            }
        }

        val newIndex = when (opCode) {
            OpCode.ADD,
            OpCode.MULTIPLY,
            OpCode.LESS_THAN,
            OpCode.EQUAL -> currentIndex + 4

            OpCode.SET,
            OpCode.PRINT -> currentIndex + 2

            OpCode.JUMP_IF_TRUE -> if (firstParamValue != 0) secondParamValue else currentIndex + 3
            OpCode.JUMP_IF_FALSE -> if (firstParamValue == 0) secondParamValue else currentIndex + 3
        }

        return doRun(inputSetting = inputSetting, currentIndex = newIndex, output = newOutput ?: output)
    }

    private enum class OpCode(val value: Int, val supportsMultipleParameters: Boolean = true) {

        ADD(1),
        MULTIPLY(2),
        SET(3, false),
        PRINT(4, false),
        JUMP_IF_TRUE(5),
        JUMP_IF_FALSE(6),
        LESS_THAN(7),
        EQUAL(8);

        companion object {
            fun from(value: Int): OpCode =
                values().find { it.value == value } ?: throw IllegalArgumentException()
        }
    }

    companion object {
        private const val POSITION_MODE = '0'
    }
}