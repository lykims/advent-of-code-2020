// Day 8: Handheld Halting

import Operation.ACC
import Operation.JMP
import Operation.NOP

class Day8 {

    private val instructionRegex = Regex("(\\S+) ([+|-]\\d+)")

    fun getAcc(
        input: List<String>
    ): Int {
        val instructions = toInstructions(input)
        return Program(instructions).getAcc()
    }

    fun getAcc2(
        input: List<String>
    ): Int {
        val instructions = toInstructions(input)
        instructions.forEachIndexed { i, instruction ->
            if (instruction.operation != ACC) {
                val newInstructionList = toNewInstructionList(instruction, instructions, i)
                val program = Program(newInstructionList)
                val programAcc = program.getAcc()
                if (!program.hasLoop) {
                    return programAcc
                }
            }
        }
        return -1
    }

    private fun toNewInstructionList(
        instruction: Instruction,
        instructions: List<Instruction>,
        index: Int
    ): MutableList<Instruction> {
        val newOperation = if (instruction.operation == JMP) NOP else JMP
        val newInstructionList = instructions.toMutableList()
        newInstructionList[index] = Instruction(newOperation, instruction.value)
        newInstructionList.forEach { it.visited = false }
        return newInstructionList
    }

    private fun toInstructions(input: List<String>): List<Instruction> {
        return input.map {
            val (operation, value) = instructionRegex.find(it)!!.destructured
            Instruction(enumValueOf(operation.uppercase()), value.toInt())
        }
    }
}

private class Program(private val instructions: List<Instruction>) {
    var hasLoop = false

    fun getAcc(): Int {
        var acc = 0
        var i = 0
        while (!hasLoop && i in instructions.indices) {
            val instruction = instructions[i]
            if (!instruction.visited) {
                instruction.visited = true
                when (instruction.operation) {
                    ACC -> {
                        acc += instruction.value
                        i++
                    }
                    JMP -> i += instruction.value
                    else -> i++
                }
            } else {
                hasLoop = true
            }
        }
        return acc
    }
}

private data class Instruction(
    val operation: Operation,
    val value: Int,
    var visited: Boolean = false
)

private enum class Operation {
    ACC,
    JMP,
    NOP
}
