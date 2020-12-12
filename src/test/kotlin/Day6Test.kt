import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day6Test {

    private val operation: Day6 = Day6()

    @Test
    fun test() {
        // Given
        val input = listOf(
            "abc",
            "",
            "a",
            "b",
            "c",
            "",
            "ab",
            "ac",
            "",
            "a",
            "a",
            "a",
            "a",
            "",
            "b"
        )

        // When
        val customAnswerCount = operation.getCustomAnswerCount(input)

        // Then
        assertEquals(11, customAnswerCount)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day6Test::class.java.getResource("day-6-input.txt").file)
            .readLines()

        // When
        val customAnswerCount = operation.getCustomAnswerCount(input)

        // Then
        assertEquals(6911, customAnswerCount)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            "abc",
            "",
            "a",
            "b",
            "c",
            "",
            "ab",
            "ac",
            "",
            "a",
            "a",
            "a",
            "a",
            "",
            "b"
        )

        // When
        val customAnswerCount = operation.getCustomAnswerCount2(input)

        // Then
        assertEquals(6, customAnswerCount)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day6Test::class.java.getResource("day-6-input.txt").file)
            .readLines()

        // When
        val customAnswerCount = operation.getCustomAnswerCount2(input)

        // Then
        assertEquals(3473, customAnswerCount)
    }
}