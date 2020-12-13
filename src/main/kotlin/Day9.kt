// Day 9: Encoding Error
class Day9 {

    fun findError(
        input: List<Long>,
        preambleSize: Int
    ): Long? {
        val preamble: MutableList<Long> = mutableListOf()
        preamble.addAll(input.subList(0, preambleSize))
        for (i in preambleSize until input.size) {
            val number = input[i]
            var foundPair = false
            for (preambleNumber in preamble) {
                val complement = number - preambleNumber
                if (preamble.contains(complement)) {
                    foundPair = true
                }
            }
            if (foundPair) {
                preamble.removeAt(0)
                preamble.add(number)
            } else {
                return number
            }
        }
        return null
    }

    fun findErrorSum(
        input: List<Long>,
        preambleSize: Int
    ): Long? {
        val errorNumber = findError(input, preambleSize)!!

        val contiguousSet = mutableListOf<Long>()
        var i = 0
        while (i < input.size) {
            val sum = if (contiguousSet.isNotEmpty()) contiguousSet.reduce { acc, l -> acc + l } else 0
            if (sum < errorNumber) {
                contiguousSet.add(input[i])
                i++
            } else if (sum > errorNumber) {
                contiguousSet.removeAt(0)
            } else {
                break
            }
        }

        if (contiguousSet.isNotEmpty()) {
            return (contiguousSet.minOrNull() ?: 0L) + (contiguousSet.maxOrNull() ?: 0L)
        }
        return null
    }
}