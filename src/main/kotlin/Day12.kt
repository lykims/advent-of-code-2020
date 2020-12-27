// Day 12: Rain Risk

import kotlin.math.abs

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
        waypoint: Waypoint
    ): Long {
        val ship = Ship()
        for (instruction in input) {
            val (action, actionValue) = instructionRegex.find(instruction)!!.destructured
            val value = actionValue.toInt()
            if (action == "L" || action == "R") {
                changeWaypointDirection(waypoint, action, value)
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
        action: String,
        value: Int
    ) {
        val isClockwise = action == "R"
        var angle = value
        while (angle >= 90) {
            val x = if (isClockwise) waypoint.y else -waypoint.y
            val y = if (isClockwise) -waypoint.x else waypoint.x
            waypoint.x = x
            waypoint.y = y
            angle -= 90
        }
    }

    private fun move(
        point: Point,
        direction: String,
        value: Int
    ) {
        when (direction) {
            "N" -> point.y += value
            "S" -> point.y -= value
            "E" -> point.x += value
            "W" -> point.x -= value
        }
    }

    private fun move(
        ship: Ship,
        waypoint: Waypoint,
        value: Int
    ) {
        ship.x += waypoint.x * value
        ship.y += waypoint.y * value
    }
}

data class Ship(
    override var x: Long = 0L,
    override var y: Long = 0L
) : Point {
    fun getTotalDistance() = abs(x) + abs(y)
}

data class Waypoint(
    override var x: Long = 0L,
    override var y: Long = 0L
) : Point

private interface Point {
    var x: Long
    var y: Long
}