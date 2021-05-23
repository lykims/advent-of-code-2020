// Day 16: Ticket Translation
class Day16 {

    fun getErrorRate(
        input: List<String>
    ): Long {
        val ticketTranslation = TicketTranslation.build(input)
        return ticketTranslation.nearbyTickets
            .flatten()
            .filter { number -> ticketTranslation.rules.all { !it.accepts(number) } }
            .sum()
    }

    fun getMyDeparture(
        input: List<String>
    ): Long {
        val ticketTranslation = TicketTranslation.build(input)
        val validTickets = ticketTranslation.nearbyTickets
            .filter { ticket -> ticket.all { number -> ticketTranslation.rules.any { it.accepts(number) } } }
            .toList()
        val fieldIndices = toFieldIndices(ticketTranslation, validTickets)
        return getDepartureNumbers(fieldIndices, ticketTranslation.myTicket).reduce { acc, number -> acc * number }
    }

    private fun getDepartureNumbers(
        fieldIndices: Map<Int, String>,
        ticket: List<Long>
    ): List<Long> {
        val departureIndices = fieldIndices
            .filter { it.value.startsWith("departure") }
            .keys
        return ticket.filterIndexed { index, _ -> departureIndices.contains(index) }
    }

    private fun toFieldIndices(
        ticketTranslation: TicketTranslation,
        validTickets: List<List<Long>>
    ): Map<Int, String> {
        val fieldIndices = mapFieldsToIndex(ticketTranslation, validTickets)
        return toUniqueFieldIndices(fieldIndices)
    }

    private fun mapFieldsToIndex(
        ticketTranslation: TicketTranslation,
        validTickets: List<List<Long>>
    ): MutableMap<Int, MutableList<String>> {
        val fieldIndices = mutableMapOf<Int, MutableList<String>>()
        for (index in ticketTranslation.rules.indices) {
            val numbers = validTickets.map { it[index] }.toList()
            fieldIndices[index] = ticketTranslation.rules
                .filter { rule -> numbers.all { rule.accepts(it) } }
                .map(FieldRule::name)
                .toMutableList()
        }
        return fieldIndices
    }

    private fun toUniqueFieldIndices(
        fieldIndices: MutableMap<Int, MutableList<String>>
    ): Map<Int, String> {
        val visitedIndices = mutableSetOf<Int>()
        var singleFieldIndex: Pair<Int, MutableList<String>>?
        while (fieldIndices.any { it.value.size > 1 }) {
            singleFieldIndex = getFirstSingleFieldIndex(fieldIndices, visitedIndices)
            removeDuplicatedFields(singleFieldIndex, fieldIndices)
            visitedIndices.add(singleFieldIndex.first)
        }
        return fieldIndices.map { Pair(it.key, it.value.first()) }.toMap()
    }

    private fun getFirstSingleFieldIndex(
        fieldIndices: MutableMap<Int, MutableList<String>>,
        visitedIndices: MutableSet<Int>
    ): Pair<Int, MutableList<String>> {
        return fieldIndices
            .filter { it.value.size == 1 && !visitedIndices.contains(it.key) }
            .toList()
            .first()
    }

    private fun removeDuplicatedFields(
        singleFieldIndex: Pair<Int, MutableList<String>>,
        fieldIndices: MutableMap<Int, MutableList<String>>
    ) {
        val fieldIndex = singleFieldIndex.first
        val fieldName = singleFieldIndex.second.first()
        for (entry in fieldIndices) {
            if (entry.key != fieldIndex && entry.value.contains(fieldName)) {
                entry.value.remove(fieldName)
            }
        }
    }
}

private data class TicketTranslation(
    val rules: List<FieldRule>,
    val myTicket: List<Long>,
    val nearbyTickets: List<List<Long>>
) {
    companion object {
        fun build(
            input: List<String>
        ): TicketTranslation {
            val rules = input
                .takeWhile { it.isNotBlank() }
                .map { FieldRule.build(it) }
            val myTicket = input
                .dropWhile { !it.contains("your ticket") }
                .drop(1)
                .first()
                .split(",")
                .map(String::toLong)
            val nearbyTickets = input
                .dropWhile { !it.contains("nearby tickets") }
                .drop(1)
                .map { it.split(",").map(String::toLong) }
            return TicketTranslation(rules, myTicket, nearbyTickets)
        }
    }
}

private data class FieldRule(
    val name: String,
    val ranges: List<LongRange>
) {
    fun accepts(number: Long): Boolean = ranges.any { it.contains(number) }

    companion object {
        private val FIELD_RULE_REGEX = Regex("(\\S+ ?\\S+): (\\d+-\\d+) or (\\d+-\\d+)")

        fun build(
            line: String
        ): FieldRule {
            val (name, range1, range2) = FIELD_RULE_REGEX.find(line)!!.destructured
            val firstRange = range1.split("-")
            val secondRange = range2.split("-")
            val ranges = listOf(
                LongRange(firstRange[0].toLong(), firstRange[1].toLong()),
                LongRange(secondRange[0].toLong(), secondRange[1].toLong())
            )
            return FieldRule(name, ranges)
        }
    }
}
