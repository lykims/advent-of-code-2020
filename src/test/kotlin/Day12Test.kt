import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day12Test {

    private val operation: Day12 = Day12()

    @Test
    fun test() {
        // Given
        val input = listOf(
            "F10",
            "N3",
            "F7",
            "R90",
            "F11"
        )

        // When
        val shipDistance = operation.getShipDistance(input, "E")

        // Then
        assertEquals(25L, shipDistance)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day12Test::class.java.getResource("day-12-input.txt").file)
            .readLines()

        // When
        val shipDistance = operation.getShipDistance(input, "E")

        // Then
        assertEquals(2458L, shipDistance)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            "F10",
            "N3",
            "F7",
            "R90",
            "F11"
        )

        // When
        val shipDistance = operation.getShipDistance2(input, Waypoint(x = 10, y = 1))

        // Then
        assertEquals(286L, shipDistance)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day12Test::class.java.getResource("day-12-input.txt").file)
            .readLines()

        // When
        val shipDistance = operation.getShipDistance2(input, Waypoint(x = 10, y = 1))

        // Then
        assertEquals(145117L, shipDistance)
    }

    @Test
    fun testNR90() {
        assertEquals("E", operation.changeDirection("N", "R", 90))
    }

    @Test
    fun testNR180() {
        assertEquals("S", operation.changeDirection("N", "R", 180))
    }

    @Test
    fun testNR270() {
        assertEquals("W", operation.changeDirection("N", "R", 270))
    }

    @Test
    fun testNR360() {
        assertEquals("N", operation.changeDirection("N", "R", 360))
    }

    @Test
    fun testNL90() {
        assertEquals("W", operation.changeDirection("N", "L", 90))
    }

    @Test
    fun testNL180() {
        assertEquals("S", operation.changeDirection("N", "L", 180))
    }

    @Test
    fun testNL270() {
        assertEquals("E", operation.changeDirection("N", "L", 270))
    }

    @Test
    fun testNL360() {
        assertEquals("N", operation.changeDirection("N", "L", 360))
    }

    @Test
    fun testER90() {
        assertEquals("S", operation.changeDirection("E", "R", 90))
    }

    @Test
    fun testER180() {
        assertEquals("W", operation.changeDirection("E", "R", 180))
    }

    @Test
    fun testER270() {
        assertEquals("N", operation.changeDirection("E", "R", 270))
    }

    @Test
    fun testER360() {
        assertEquals("E", operation.changeDirection("E", "R", 360))
    }

    @Test
    fun testEL90() {
        assertEquals("N", operation.changeDirection("E", "L", 90))
    }

    @Test
    fun testEL180() {
        assertEquals("W", operation.changeDirection("E", "L", 180))
    }

    @Test
    fun testEL270() {
        assertEquals("S", operation.changeDirection("E", "L", 270))
    }

    @Test
    fun testEL360() {
        assertEquals("E", operation.changeDirection("E", "L", 360))
    }

    @Test
    fun testSR90() {
        assertEquals("W", operation.changeDirection("S", "R", 90))
    }

    @Test
    fun testSR180() {
        assertEquals("N", operation.changeDirection("S", "R", 180))
    }

    @Test
    fun testSR270() {
        assertEquals("E", operation.changeDirection("S", "R", 270))
    }

    @Test
    fun testSR360() {
        assertEquals("S", operation.changeDirection("S", "R", 360))
    }

    @Test
    fun testSL90() {
        assertEquals("E", operation.changeDirection("S", "L", 90))
    }

    @Test
    fun testSL180() {
        assertEquals("N", operation.changeDirection("S", "L", 180))
    }

    @Test
    fun testSL270() {
        assertEquals("W", operation.changeDirection("S", "L", 270))
    }

    @Test
    fun testSL360() {
        assertEquals("S", operation.changeDirection("S", "L", 360))
    }

    @Test
    fun testWR90() {
        assertEquals("N", operation.changeDirection("W", "R", 90))
    }

    @Test
    fun testWR180() {
        assertEquals("E", operation.changeDirection("W", "R", 180))
    }

    @Test
    fun testWR270() {
        assertEquals("S", operation.changeDirection("W", "R", 270))
    }

    @Test
    fun testWR360() {
        assertEquals("W", operation.changeDirection("W", "R", 360))
    }

    @Test
    fun testWL90() {
        assertEquals("S", operation.changeDirection("W", "L", 90))
    }

    @Test
    fun testWL180() {
        assertEquals("E", operation.changeDirection("W", "L", 180))
    }

    @Test
    fun testWL270() {
        assertEquals("N", operation.changeDirection("W", "L", 270))
    }

    @Test
    fun testWL360() {
        assertEquals("W", operation.changeDirection("W", "L", 360))
    }
}