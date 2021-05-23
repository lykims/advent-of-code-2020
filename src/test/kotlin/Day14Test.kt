import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day14Test {

    private val operation: Day14 = Day14()

    @Test
    fun test() {
        // Given
        val input = listOf(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
        )

        // When
        val sum = operation.getSum(input)

        // Then
        assertEquals(165, sum)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day14Test::class.java.getResource("day-14-input.txt").file)
            .readLines()

        // When
        val sum = operation.getSum(input)

        // Then
        assertEquals(9296748256641, sum)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"
        )

        // When
        val sum = operation.getSum2(input)

        // Then
        assertEquals(208, sum)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day14Test::class.java.getResource("day-14-input.txt").file)
            .readLines()

        // When
        val sum = operation.getSum2(input)

        // Then
        assertEquals(4877695371685, sum)
    }
}