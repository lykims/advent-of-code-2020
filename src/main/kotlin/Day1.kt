// Day 1: Report Repair
class Day1 {

    fun reportRepair(
        input: List<Int>,
        sumTarget: Int
    ): Int {
        for (entry in input) {
            val complement: Int = sumTarget - entry
            if (input.contains(complement)) {
                return complement * entry
            }
        }
        throw Error("Cannot find the pair of entries")
    }

    fun reportRepair2(
        input: List<Int>,
        sumTarget: Int
    ): Int {
        val sumTrios: MutableList<MutableList<Int>> = mutableListOf()

        for (entry in input) {

            val sumTriosListSize: Int = sumTrios.size - 1
            for (i in 0..sumTriosListSize) {
                val sumEntries: MutableList<Int> = sumTrios[i]
                    .toMutableList()
                    .apply { add(entry) }
                val sum = sumEntries.reduce { acc, j ->  acc + j }
                if (sum < 2020) {
                    sumTrios.add(sumEntries)
                } else if (sum == 2020 && sumEntries.size == 3) {
                    return sumEntries.reduce { acc, j ->  acc * j }
                }
            }

            if (entry < sumTarget) {
                sumTrios.add(mutableListOf(entry))
            }
        }

        throw Error("Cannot find the trio of entries")
    }
}