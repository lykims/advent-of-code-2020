// Day 15: Rambunctious Recitation
class Day15 {

    fun getNthSpokenNumber(
        numbers: List<Int>,
        nth: Int
    ): Int {
        val spokenNumbers = mutableMapOf<Int, IntArray>()
        numbers.forEachIndexed { index, number -> spokenNumbers[number] = intArrayOf(0, index + 1) }
        var lastSpokenNumber = numbers.last()
        for (n in numbers.size + 1 until nth + 1) {
            if (isFirstTimeSpoken(lastSpokenNumber, spokenNumbers)) {
                lastSpokenNumber = 0
            } else {
                if (spokenNumbers.containsKey(lastSpokenNumber)) {
                    val turns = spokenNumbers[lastSpokenNumber]!!
                    lastSpokenNumber = turns[1] - turns[0]
                }
            }
            updateNumberTurns(spokenNumbers, lastSpokenNumber, n)
        }
        return lastSpokenNumber
    }

    private fun updateNumberTurns(
        spokenNumbers: MutableMap<Int, IntArray>,
        number: Int,
        n: Int
    ) {
        if (spokenNumbers.containsKey(number)) {
            val turns = spokenNumbers[number]!!
            turns[0] = turns[1]
            turns[1] = n
        } else {
            spokenNumbers[number] = intArrayOf(0, n)
        }
    }

    private fun isFirstTimeSpoken(
        number: Int,
        spokenNumbers: MutableMap<Int, IntArray>
    ): Boolean = spokenNumbers[number]?.let { turns -> turns[1] - turns[0] == turns[1] } ?: true
}