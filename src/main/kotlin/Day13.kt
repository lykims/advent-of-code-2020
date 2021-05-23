// Day 13: Shuttle Search
class Day13 {

    fun getEarliestBus(
        input: List<String>
    ): Long {
        val departureTimestamp = input[0].toLong()
        val buses: List<Long> = input[1].split(",").filter { it != "x" }.map { it.toLong() }

        var timestamp = departureTimestamp
        var bus: Long? = null
        while (bus == null) {
            bus = buses.find { timestamp % it == 0L }
            if (bus != null) {
                return bus * (timestamp - departureTimestamp)
            }
            timestamp++
        }
        return 0
    }

    fun getEarliestTimestamp(
        input: String
    ): Long {
        val indexedBuses = input.split(",").withIndex().filter { it.value != "x" }
        val n = indexedBuses.map { it.value.toLong() }.toLongArray()
        val a = indexedBuses.map { it.value.toLong() - it.index }.toLongArray()
        return chineseRemainder(n, a)
    }

    // Source: https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
    private fun chineseRemainder(n: LongArray, a: LongArray): Long {
        val prod = n.fold(1L) { acc, i -> acc * i }
        var sum = 0L
        for (i in n.indices) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }

    private fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1L) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0L) x1 += b
        return x1
    }
}
