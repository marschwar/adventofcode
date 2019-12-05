package de.marschwar.adventofcode.v2019

import de.marschwar.adventofcode.Puzzle

class Day5() : Puzzle() {

    override fun part1(input: Sequence<String>): Any {
        return input.toProgram().doRun(1)
    }

    override fun part2(input: Sequence<String>): Any {
        return input.toProgram().doRun(5)
    }

    private fun Sequence<String>.toProgram(): Program5 =
        this.first()
            .split(",")
            .map(String::toInt)
            .toMutableList()

    private tailrec fun Program5.doRun(systemId: Int, currentIndex: Int = 0, output: String = ""): String {
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
            if (opCode.supportsMultipleParametes && modes[2] == POSITION_MODE) get(secondParam) else secondParam

        val targetIndex = get(currentIndex + 3)
        var nextOutput = output

        when (opCode) {
            OpCode.ADD -> set(targetIndex, firstParamValue + secondParamValue)
            OpCode.MULTIPLY -> set(targetIndex, firstParamValue * secondParamValue)
            OpCode.SET -> set(firstParam, systemId)
            OpCode.PRINT -> nextOutput += "|$firstParamValue"
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

        return doRun(systemId = systemId, currentIndex = newIndex, output = nextOutput)
    }

    private enum class OpCode(val value: Int, val supportsMultipleParametes: Boolean = true) {

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

private typealias Program5 = MutableList<Int>

fun main() = Day5().solve()
