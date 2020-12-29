import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day10Test {

    private val operation: Day10 = Day10()

    @Test
    fun test() {
        // Given
        val input = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
            .map { it.toLong() }

        // When
        val joltDifference = operation.getJoltDifference(input)

        // Then
        assertEquals(7*5, joltDifference)
    }

    @Test
    fun test2() {
        // Given
        val input = listOf(
                28, 33, 18, 42, 31, 14, 46, 20, 48, 47,
                24, 23, 49, 45, 19, 38, 39, 11, 1, 32,
                25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3
            )
            .map { it.toLong() }

        // When
        val joltDifference = operation.getJoltDifference(input)

        // Then
        assertEquals(22*10, joltDifference)
    }

    @Test
    fun test3() {
        // Given
        val input = File(Day10Test::class.java.getResource("day-10-input.txt").file)
            .readLines()
            .map { it.toLong() }

        // When
        val joltDifference = operation.getJoltDifference(input)

        // Then
        assertEquals(70*29, joltDifference)
    }

    @Test
    fun test4() {
        // Given
        val input = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
            .map { it.toLong() }
            .toMutableList()

        // When
        val count = operation.getJoltArrangementCount(input)

        // Then
        assertEquals(8, count)
    }

    @Test
    fun test5() {
        // Given
        val input = listOf(
                28, 33, 18, 42, 31, 14, 46, 20, 48, 47,
                24, 23, 49, 45, 19, 38, 39, 11, 1, 32,
                25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3
            )
            .map { it.toLong() }
            .toMutableList()

        // When
        val count = operation.getJoltArrangementCount(input)

        // Then
        assertEquals(19208, count)
    }

    @Test
    fun test6() {
        // Given
        val input = File(Day10Test::class.java.getResource("day-10-input.txt").file)
            .readLines()
            .map { it.toLong() }
            .toMutableList()

        // When
        val count = operation.getJoltArrangementCount(input)

        // Then
        assertEquals(42313823813632, count)
    }
}