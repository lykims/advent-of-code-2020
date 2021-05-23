import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day13Test {

    private val operation: Day13 = Day13()

    @Test
    fun test() {
        // Given
        val input = listOf("939", "7,13,x,x,59,x,31,19")

        // When
        val bus = operation.getEarliestBus(input)

        // Then
        assertEquals(59 * 5, bus)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day13Test::class.java.getResource("day-13-input.txt").file)
            .readLines()

        // When
        val bus = operation.getEarliestBus(input)

        // Then
        assertEquals(17 * 6, bus)
    }

    @Test
    fun test3() {
        // Given
        val input = "7,13,x,x,59,x,31,19"

        // When
        val timestamp = operation.getEarliestTimestamp(input)

        // Then
        assertEquals(1068781, timestamp)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day13Test::class.java.getResource("day-13-input.txt").file)
            .readLines()[1]

        // When
        val timestamp = operation.getEarliestTimestamp(input)

        // Then
        assertEquals(327300950120029, timestamp)
    }

    @Test
    fun test5() {
        // Given
        val input = "17,x,13,19"

        // When
        val timestamp = operation.getEarliestTimestamp(input)

        // Then
        assertEquals(3417, timestamp)
    }

    @Test
    fun test6() {
        // Given
        val input = "67,7,59,61"

        // When
        val timestamp = operation.getEarliestTimestamp(input)

        // Then
        assertEquals(754018, timestamp)
    }

    @Test
    fun test7() {
        // Given
        val input = "67,x,7,59,61"

        // When
        val timestamp = operation.getEarliestTimestamp(input)

        // Then
        assertEquals(779210, timestamp)
    }

    @Test
    fun test8() {
        // Given
        val input = "67,7,x,59,61"

        // When
        val timestamp = operation.getEarliestTimestamp(input)

        // Then
        assertEquals(1261476, timestamp)
    }

    @Test
    fun test9() {
        // Given
        val input = "1789,37,47,1889"

        // When
        val timestamp = operation.getEarliestTimestamp(input)

        // Then
        assertEquals(1202161486, timestamp)
    }
}
