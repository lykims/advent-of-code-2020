import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day9Test {

    private val operation: Day9 = Day9()

    @Test
    fun test() {
        // Given
        val input = listOf(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576
        ).map { it.toLong() }

        // When
        val errorNumber = operation.findError(input, 5)

        // Then
        assertEquals(127L, errorNumber)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day9Test::class.java.getResource("day-9-input.txt").file)
            .readLines()
            .map { it.toLong() }

        // When
        val errorNumber = operation.findError(input, 25)

        // Then
        assertEquals(1721308972L, errorNumber)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576
        ).map { it.toLong() }

        // When
        val errorNumber = operation.findErrorSum(input, 5)

        // Then
        assertEquals(62L, errorNumber)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day9Test::class.java.getResource("day-9-input.txt").file)
            .readLines()
            .map { it.toLong() }

        // When
        val errorNumber = operation.findErrorSum(input, 25)

        // Then
        assertEquals(209694133L, errorNumber)
    }
}
