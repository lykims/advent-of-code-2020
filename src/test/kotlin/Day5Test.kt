import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day5Test {

    private val operation: Day5 = Day5()

    @Test
    fun `should decode FBFBBFFRLR`() {
        // Given
        val input = "FBFBBFFRLR"

        // When
        val seatId = operation.decodeBoardingPass(input)

        // Then
        assertEquals(357, seatId)
    }

    @Test
    fun `should decode BFFFBBFRRR`() {
        // Given
        val input = "BFFFBBFRRR"

        // When
        val seatId = operation.decodeBoardingPass(input)

        // Then
        assertEquals(567, seatId)
    }

    @Test
    fun `should decode FFFBBBFRRR`() {
        // Given
        val input = "FFFBBBFRRR"

        // When
        val seatId = operation.decodeBoardingPass(input)

        // Then
        assertEquals(119, seatId)
    }

    @Test
    fun `should decode BBFFBBFRLL`() {
        // Given
        val input = "BBFFBBFRLL"

        // When
        val seatId = operation.decodeBoardingPass(input)

        // Then
        assertEquals(820, seatId)
    }

    @Test
    fun test() {
        // Given
        val input = File(Day5Test::class.java.getResource("day-5-input.txt").file)
            .readLines()

        // When
        val highestSeatId = operation.getHighestSeatId(input)

        // Then
        assertEquals(806, highestSeatId)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day5Test::class.java.getResource("day-5-input.txt").file)
            .readLines()

        // When
        val mySeat = operation.findMySeat(input)

        // Then
        assertEquals(562, mySeat)
    }
}
