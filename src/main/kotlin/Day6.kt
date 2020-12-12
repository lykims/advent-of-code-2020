// Day 6: Custom Customs
class Day6 {

    fun getCustomAnswerCount(
        input: List<String>
    ): Int {
        val groups = toGroups(input)
        return groups.map { it.answers.joinToString("").toSet().size }.reduce { acc, i -> acc + i }
    }

    fun getCustomAnswerCount2(
        input: List<String>
    ): Int {
        val groups = toGroups(input)
        return groups.sumBy {
            it.answers.reduce {
                a, b -> a.toSet().intersect(b.toSet()).joinToString("")
            }.length
        }
    }

    private fun toGroups(input: List<String>): List<Group> {
        val groups = mutableListOf<Group>()
        var answers = mutableListOf<String>()
        for (line in input) {
            if (line.trim().isEmpty()) {
                if (answers.isNotEmpty()) {
                    groups.add(Group(answers))
                    answers = mutableListOf()
                }
            } else {
                answers.add(line)
            }
        }
        groups.add(Group(answers))
        return groups
    }
}

private data class Group(
    val answers: List<String>
)