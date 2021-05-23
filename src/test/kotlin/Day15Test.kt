import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

internal class Day15Test {

    private val operation: Day15 = Day15()

    @ParameterizedTest
    @MethodSource("numbers")
    fun test(numbers: List<Int>, nth: Int, expected: Int) {
        // When
        val number = operation.getNthSpokenNumber(numbers, nth)

        // Then
        assertEquals(expected, number)
    }

    @Test
    fun test2() {
        // Given
        val numbers = File(Day15Test::class.java.getResource("day-15-input.txt").file)
            .readLines()[0]
            .split(",")
            .map { it.toInt() }
        val nth = 2020

        // When
        val number = operation.getNthSpokenNumber(numbers, nth)

        // Then
        assertEquals(1009, number)
    }

    @Test
    fun test3() {
        // Given
        val numbers = File(Day15Test::class.java.getResource("day-15-input.txt").file)
            .readLines()[0]
            .split(",")
            .map { it.toInt() }
        val nth = 30000000

        // When
        val number = operation.getNthSpokenNumber(numbers, nth)

        // Then
        assertEquals(62714, number)
    }

    companion object {
        @JvmStatic
        fun numbers() = listOf(
            Arguments.of(listOf(0, 3, 6), 2020, 436),
            Arguments.of(listOf(1, 3, 2), 2020, 1),
            Arguments.of(listOf(2, 1, 3), 2020, 10),
            Arguments.of(listOf(1, 2, 3), 2020, 27),
            Arguments.of(listOf(2, 3, 1), 2020, 78),
            Arguments.of(listOf(3, 2, 1), 2020, 438),
            Arguments.of(listOf(3, 1, 2), 2020, 1836)
        )
    }
}