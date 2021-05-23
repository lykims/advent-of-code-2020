// Day 11: Seating System
class Day11 {

    fun getAdjacentOccupiedSeatCount(
        input: List<String>
    ): Int {
        return getOccupiedSeatCount(input, ::hasGreaterOrEqualAdjacentOccupiedSeats, 4)
    }

    fun getVisibleOccupiedSeatCount(
        input: List<String>
    ): Int {
        return getOccupiedSeatCount(input, ::hasGreaterOrEqualVisibleOccupiedSeats, 5)
    }

    private fun getOccupiedSeatCount(
        input: List<String>,
        hasGreaterOrEqualOccupiedSeats: (number: Int, layout: Array<CharArray>, spacePoint: SpacePoint) -> Boolean,
        maxOccupiedSeats: Int
    ): Int {
        var previousLayout: Array<CharArray>? = null
        val nextLayout = toLayoutArray(input)
        while (previousLayout?.contentDeepEquals(nextLayout) != true) {
            previousLayout = nextLayout.copyOf().map { it.copyOf() }.toTypedArray()
            for (i in previousLayout.indices) {
                for (j in previousLayout[i].indices) {
                    val spacePoint = SpacePoint(j, i)
                    if (isEmptySeatToOccupy(spacePoint, previousLayout, hasGreaterOrEqualOccupiedSeats)) {
                        nextLayout[i][j] = '#'
                    } else if (isOccupiedSeatToEmpty(spacePoint, previousLayout, hasGreaterOrEqualOccupiedSeats, maxOccupiedSeats)) {
                        nextLayout[i][j] = 'L'
                    }
                }
            }
        }
        return nextLayout.sumOf { row -> row.count { space -> space == '#' } }
    }

    private fun isEmptySeatToOccupy(
        spacePoint: SpacePoint,
        previousLayout: Array<CharArray>,
        hasGreaterOrEqualOccupiedSeats: (number: Int, layout: Array<CharArray>, spacePoint: SpacePoint) -> Boolean
    ): Boolean {
        return previousLayout[spacePoint.y][spacePoint.x] == 'L' &&
            !hasGreaterOrEqualOccupiedSeats(1, previousLayout, spacePoint)
    }

    private fun isOccupiedSeatToEmpty(
        spacePoint: SpacePoint,
        previousLayout: Array<CharArray>,
        hasGreaterOrEqualOccupiedSeats: (number: Int, layout: Array<CharArray>, spacePoint: SpacePoint) -> Boolean,
        maxNumberOfOccupiedSeats: Int
    ): Boolean {
        return previousLayout[spacePoint.y][spacePoint.x] == '#' &&
            hasGreaterOrEqualOccupiedSeats(maxNumberOfOccupiedSeats, previousLayout, spacePoint)
    }

    private fun hasGreaterOrEqualAdjacentOccupiedSeats(
        numberOfOccupiedSeats: Int,
        layout: Array<CharArray>,
        spacePoint: SpacePoint
    ): Boolean {
        var count = 0
        for (direction in DIRECTIONS) {
            val adjacentSpacePoint = SpacePoint(spacePoint.x + direction.x, spacePoint.y + direction.y)
            if (isInLayoutBound(layout, adjacentSpacePoint)) {
                val space = layout[adjacentSpacePoint.y][adjacentSpacePoint.x]
                if (space == '#') {
                    count++
                }
            }
        }
        return count >= numberOfOccupiedSeats
    }

    private fun hasGreaterOrEqualVisibleOccupiedSeats(
        numberOfOccupiedSeats: Int,
        layout: Array<CharArray>,
        spacePoint: SpacePoint
    ): Boolean {
        return getVisibleOccupiedSeatCount(layout, spacePoint) >= numberOfOccupiedSeats
    }

    fun getVisibleOccupiedSeatCount(
        layout: Array<CharArray>,
        spacePoint: SpacePoint
    ): Int {
        return DIRECTIONS.sumOf { getVisibleOccupiedSeatCount(layout, spacePoint, it) }
    }

    private fun getVisibleOccupiedSeatCount(
        layout: Array<CharArray>,
        spacePoint: SpacePoint,
        direction: SpacePoint
    ): Int {
        val visibleSpacePoint = SpacePoint(spacePoint.x + direction.x, spacePoint.y + direction.y)
        while (isInLayoutBound(layout, visibleSpacePoint)) {
            val space = layout[visibleSpacePoint.y][visibleSpacePoint.x]
            if (space == 'L') {
                return 0
            } else if (space == '#') {
                return 1
            }
            visibleSpacePoint.x += direction.x
            visibleSpacePoint.y += direction.y
        }
        return 0
    }

    private fun isInLayoutBound(
        layout: Array<CharArray>,
        spacePoint: SpacePoint
    ): Boolean = spacePoint.y in layout.indices && spacePoint.x in layout[0].indices

    fun toLayoutArray(
        input: List<String>
    ): Array<CharArray> {
        val array = Array(input.size) { CharArray(input[0].length) }
        for (i in input.indices) {
            for (j in input[i].indices) {
                array[i][j] = input[i][j]
            }
        }
        return array
    }

    companion object {
        val DIRECTIONS: List<SpacePoint> = listOf(
            SpacePoint(0, 1),
            SpacePoint(0, -1),
            SpacePoint(1, 0),
            SpacePoint(-1, 0),
            SpacePoint(-1, 1),
            SpacePoint(-1, -1),
            SpacePoint(1, 1),
            SpacePoint(1, -1)
        )
    }
}

data class SpacePoint(
    var x: Int,
    var y: Int
)
