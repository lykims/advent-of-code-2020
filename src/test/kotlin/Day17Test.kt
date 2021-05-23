import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

internal class Day17Test {

    private val operation: Day17 = Day17()

    @Test
    fun test() {
        // Given
        val input = listOf(
            ".#.",
            "..#",
            "###"
        )

        // When
        val count = operation.getActiveCubeCount(0, input, 6)

        // Then
        assertEquals(112, count)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day17Test::class.java.getResource("day-17-input.txt").file)
            .readLines()

        // When
        val count = operation.getActiveCubeCount(0, input, 6)

        // Then
        assertEquals(255, count)
    }

    @Disabled
    @Test
    fun test3() {
        // Given
        val input = listOf(
            ".#.",
            "..#",
            "###"
        )

        // When
        val count = operation.getActiveHyperCubeCount(0, 0, input, 6)

        // Then
        assertEquals(848, count)
    }

    @Disabled("test4 disabled because it runs for about 3 m 43 s 65 ms")
    @Test
    fun test4() {
        // Given
        val input = File(Day17Test::class.java.getResource("day-17-input.txt").file)
            .readLines()

        // When
        val count = operation.getActiveHyperCubeCount(0, 0, input, 6)

        // Then
        assertEquals(2340, count)
    }
}
