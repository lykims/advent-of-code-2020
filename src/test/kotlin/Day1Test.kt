import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day1Test {

    private val operation: Day1 = Day1()

    @Test
    fun test1() {
        // Given
        val input: List<Int> = listOf(
            1721,
            979,
            366,
            299,
            675,
            1456
        )
        val sumTarget = 2020

        // When
        val answer: Int = operation.reportRepair(input, sumTarget)

        // Then
        assertEquals(514579, answer)
    }

    @Test
    fun test2() {
        // Given
        val input: List<Int> = File(Day1Test::class.java.getResource("day-1-input.txt").file)
            .readLines()
            .map{ it.toInt() }
        val sumTarget = 2020

        // When
        val answer: Int = operation.reportRepair(input, sumTarget)

        // Then
        assertEquals(1006176, answer)
    }

    @Test
    fun test3() {
        // Given
        val input: List<Int> = listOf(
            1721,
            979,
            366,
            299,
            675,
            1456
        )
        val sumTarget = 2020

        // When
        val answer: Int = operation.reportRepair2(input, sumTarget)

        // Then
        assertEquals(241861950, answer)
    }

    @Test
    fun test4() {
        // Given
        val input: List<Int> = File(Day1Test::class.java.getResource("day-1-input.txt").file)
            .readLines()
            .map{ it.toInt() }
        val sumTarget = 2020

        // When
        val answer: Int = operation.reportRepair2(input, sumTarget)

        // Then
        assertEquals(199132160, answer)
    }
}