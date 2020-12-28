import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day8Test {

    private val operation: Day8 = Day8()

    @Test
    fun test() {
        // Given
        val input = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
        )

        // When
        val acc = operation.getAcc(input)

        // Then
        assertEquals(5, acc)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day8Test::class.java.getResource("day-8-input.txt").file)
            .readLines()

        // When
        val acc = operation.getAcc(input)

        // Then
        assertEquals(1797, acc)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
        )

        // When
        val acc = operation.getAcc2(input)

        // Then
        assertEquals(8, acc)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day8Test::class.java.getResource("day-8-input.txt").file)
            .readLines()

        // When
        val acc = operation.getAcc2(input)

        // Then
        assertEquals(1036, acc)
    }
}