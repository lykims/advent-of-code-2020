import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day2Test {

    private val operation: Day2 = Day2()

    @Test
    fun test1() {
        // Given
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        // When
        val numberOfValidPasswords = operation.getNumberOfValidPasswords(input)

        // Then
        assertEquals(2, numberOfValidPasswords)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day2Test::class.java.getResource("day-2-input.txt").file)
            .readLines()

        // When
        val numberOfValidPasswords = operation.getNumberOfValidPasswords(input)

        // Then
        assertEquals(467, numberOfValidPasswords)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        // When
        val numberOfValidPasswords = operation.getNumberOfValidPasswords2(input)

        // Then
        assertEquals(1, numberOfValidPasswords)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day2Test::class.java.getResource("day-2-input.txt").file)
            .readLines()

        // When
        val numberOfValidPasswords = operation.getNumberOfValidPasswords2(input)

        // Then
        assertEquals(441, numberOfValidPasswords)
    }
}