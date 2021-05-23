// Day 3: Toboggan Trajectory
class Day3 {

    fun getNumberOfTreesEncountered(
        input: List<String>,
        right: Int,
        down: Int
    ): Int {
        var numberOfTrees = 0
        var x = right
        var y = down

        while (y < input.size) {
            val line = input[y]
            val lineX = x % line.length
            if (line[lineX] == '#') {
                numberOfTrees++
            }
            x += right
            y += down
        }

        return numberOfTrees
    }

    fun getNumberOfTreesEncountered2(
        input: List<String>
    ): Int {
        val results = listOf(
            getNumberOfTreesEncountered(input, 1, 1),
            getNumberOfTreesEncountered(input, 3, 1),
            getNumberOfTreesEncountered(input, 5, 1),
            getNumberOfTreesEncountered(input, 7, 1),
            getNumberOfTreesEncountered(input, 1, 2)
        )
        return results.reduce { acc, i -> acc * i }
    }
}
