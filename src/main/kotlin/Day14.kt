// Day 14: Docking Data
class Day14 {

    fun getSum(
        input: List<String>
    ): Long {
        val programs = toMemoryPrograms(input)
        val mem = mutableMapOf<Long, Long>()
        for (program in programs) {
            for (instruction in program.memInstructions) {
                mem[instruction.address] = applyMask(instruction.value, program.mask, 'X').toLong(2)
            }
        }
        return mem.values.sum()
    }

    fun getSum2(
        input: List<String>
    ): Long {
        val programs = toMemoryPrograms(input)
        val mem = mutableMapOf<Long, Long>()
        for (program in programs) {
            for (instruction in program.memInstructions) {
                val floatingMask = applyMask(instruction.address, program.mask, '0')
                getAddressCombinations(floatingMask).forEach { mem[it.toLong(2)] = instruction.value }
            }
        }
        return mem.values.sum()
    }

    private fun applyMask(
        value: Long,
        mask: String,
        unchangedBit: Char
    ): String {
        val binaryValue = value.toString(2).padStart(mask.length, '0')
        val result = StringBuilder(binaryValue)
        for (i in mask.indices) {
            val bit = mask[i]
            if (bit != unchangedBit) {
                result.setCharAt(i, bit)
            }
        }
        return result.toString()
    }

    private fun getAddressCombinations(
        binaryValue: String
    ): List<String> {
        val index = binaryValue.indexOf('X')
        if (index == -1) {
            return listOf(binaryValue)
        }
        return getAddressCombinations(binaryValue.replaceFirst('X', '0'))
            .plus(getAddressCombinations(binaryValue.replaceFirst('X', '1')))
    }

    private fun toMemoryPrograms(
        input: List<String>
    ): MutableList<MemoryProgram> {
        val programs = mutableListOf<MemoryProgram>()
        var program: MemoryProgram? = null
        for (line in input) {
            val (instruction, _, address, value) = INSTRUCTION_REGEX.find(line)!!.destructured
            if (instruction == "mask") {
                program = MemoryProgram()
                programs.add(program)
                program.mask = value
            } else {
                program?.memInstructions?.add(Memory(address.toLong(), value.toLong()))
            }
        }
        return programs
    }

    companion object {
        private val INSTRUCTION_REGEX = Regex("(mask|mem)(\\[(\\d+)\\])? = (\\S+)")
    }
}

private data class MemoryProgram(
    var mask: String = "",
    val memInstructions: MutableList<Memory> = mutableListOf()
)

private data class Memory(
    val address: Long,
    val value: Long
)
