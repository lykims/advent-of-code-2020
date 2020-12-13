// Day 12: Rain Risk
class Day12 {

    private val instructionRegex = Regex("(\\S)(\\d+)")
    private val directions = listOf("N", "E", "S", "W")

    fun getShipDistance(
        input: List<String>,
        startingDirection: String
    ): Long {
        val ship = Ship()
        var currentDirection = startingDirection
        for (instruction in input) {
            val (action, actionValue) = instructionRegex.find(instruction)!!.destructured
            val value = actionValue.toInt()
            if (action == "L" || action == "R") {
                currentDirection = changeDirection(currentDirection, action, value)
            } else if (action == "F") {
                move(ship, currentDirection, value)
            } else {
                move(ship, action, value)
            }
        }
        return ship.getTotalDistance()
    }

    fun getShipDistance2(
        input: List<String>,
        startingDirection: String,
        waypoint: Waypoint
    ): Long {
        val ship = Ship()
        var currentDirection = startingDirection
        for (instruction in input) {
            val (action, actionValue) = instructionRegex.find(instruction)!!.destructured
            val value = actionValue.toInt()
            if (action == "L" || action == "R") {
                currentDirection = changeWaypointDirection(waypoint, currentDirection, action, value)
            } else if (action == "F") {
                move(ship, waypoint, value)
            } else {
                move(waypoint, action, value)
            }
        }
        return ship.getTotalDistance()
    }

    fun changeDirection(
        currentDirection: String,
        action: String,
        value: Int
    ): String {
        val directionValue = value / 90
        val currentDirectionIndex = directions.indexOf(currentDirection)
        var index = 0
        if (action == "L") {
            val diff = currentDirectionIndex - directionValue
            index = diff
            if (diff < 0) {
                index = directions.size + diff
            }
        } else if (action == "R") {
            index = currentDirectionIndex + directionValue
        }
        return directions[index % 4]
    }

    private fun changeWaypointDirection(
        waypoint: Waypoint,
        currentDirection: String,
        action: String,
        value: Int
    ): String {
        var directionValue = value / 90
        if (action == "L") {
            directionValue *= -1
        }
        val newNorthIndex = Math.abs(0 + directionValue) % 4
        val newEastIndex = Math.abs(1 + directionValue) % 4
        val newSouthIndex = Math.abs(2 + directionValue) % 4
        val newWestIndex = Math.abs(3 + directionValue) % 4
        val newPoints = LongArray(4)
        newPoints[newNorthIndex] = waypoint.north
        newPoints[newEastIndex] = waypoint.east
        newPoints[newSouthIndex] = waypoint.south
        newPoints[newWestIndex] = waypoint.west
        waypoint.north = newPoints[0]
        waypoint.east = newPoints[1]
        waypoint.south = newPoints[2]
        waypoint.west = newPoints[3]
        return changeDirection(currentDirection, action, value)
    }

    private fun move(
        point: Point,
        direction: String,
        value: Int
    ) {
        when (direction) {
            "N" -> point.north += value
            "S" -> point.south += value
            "E" -> point.east += value
            "W" -> point.west += value
        }
    }

    private fun move(
        ship: Ship,
        waypoint: Waypoint,
        value: Int
    ) {
        ship.north += waypoint.north * value
        ship.south += waypoint.south * value
        ship.east += waypoint.east * value
        ship.west += waypoint.west * value
    }
}

data class Ship(
    override var north: Long = 0L,
    override var south: Long = 0L,
    override var east: Long = 0L,
    override var west: Long = 0L
) : Point {
    fun getTotalDistance() = Math.abs(north - south) + Math.abs(east - west)
}

data class Waypoint(
    override var north: Long = 0L,
    override var south: Long = 0L,
    override var east: Long = 0L,
    override var west: Long = 0L
) : Point

private interface Point {
    var north: Long
    var south: Long
    var east: Long
    var west: Long
}