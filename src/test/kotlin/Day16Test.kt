import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day16Test {

    private val operation: Day16 = Day16()

    @Test
    fun test() {
        // Given
        val input = listOf(
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50",
            "",
            "your ticket:",
            "7,1,14",
            "",
            "nearby tickets:",
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12"
        )

        // When
        val errorRate = operation.getErrorRate(input)

        // Then
        assertEquals(71, errorRate)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day16Test::class.java.getResource("day-16-input.txt").file)
            .readLines()

        // When
        val errorRate = operation.getErrorRate(input)

        // Then
        assertEquals(27870, errorRate)
    }

    @Test
    fun test3() {
        // Given
        val input = File(Day16Test::class.java.getResource("day-16-input.txt").file)
            .readLines()

        // When
        val departure = operation.getMyDeparture(input)

        // Then
        assertEquals(3173135507987, departure)
    }
}
