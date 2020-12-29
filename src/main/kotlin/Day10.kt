// Day 10: Adapter Array
class Day10 {

    fun getJoltDifference(
        input: List<Long>
    ): Long {
        val adapterJolts = input.quickSort()
        var previousJolt = 0L
        var oneJoltDiff = 0L
        var threeJoltDiff = 1L
        for (jolt in adapterJolts) {
            val diff = jolt - previousJolt
            if (diff == 1L) {
                previousJolt = jolt
                oneJoltDiff++
            } else if (diff == 3L) {
                previousJolt = jolt
                threeJoltDiff++
            }
        }
        return oneJoltDiff * threeJoltDiff
    }

    fun getJoltArrangementCount(
        input: MutableList<Long>
    ): Long {
        input.add(0)
        input.maxOrNull()?.let { input.add(it.plus(3)) }

        val adapterJolts = input.quickSort()

        val memo = Array(adapterJolts.size) { 0L }
        memo[0] = 1L
        for (i in adapterJolts.indices) {
            var j = i + 1
            while (j < adapterJolts.size && j <= i + 3) {
                if (adapterJolts[j] - adapterJolts[i] <= 3L) {
                    memo[j] += memo[i]
                }
                j++
            }
        }
        return memo.last()
    }
}

fun <T : Comparable<T>> List<T>.quickSort(): List<T> = when {
    size < 2 -> this
    else -> {
        val pivot = this[this.count() / 2]
        val left = this.filter { it < pivot }
        val right = this.filter { it > pivot }
        left.quickSort() + pivot + right.quickSort()
    }
}