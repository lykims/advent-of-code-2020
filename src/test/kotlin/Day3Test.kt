import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day3Test {

    private val operation: Day3 = Day3()

    @Test
    fun test1() {
        // Given
        val input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
        )

        // When
        val numberOfTreesEncountered = operation.getNumberOfTreesEncountered(input, 3, 1)

        // Then
        assertEquals(7, numberOfTreesEncountered)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day3Test::class.java.getResource("day-3-input.txt").file)
            .readLines()

        // When
        val numberOfTreesEncountered = operation.getNumberOfTreesEncountered(input, 3, 1)

        // Then
        assertEquals(225, numberOfTreesEncountered)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
        )

        // When
        val numberOfTreesEncountered = operation.getNumberOfTreesEncountered2(input)

        // Then
        assertEquals(336, numberOfTreesEncountered)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day3Test::class.java.getResource("day-3-input.txt").file)
            .readLines()

        // When
        val numberOfTreesEncountered = operation.getNumberOfTreesEncountered2(input)

        // Then
        assertEquals(1115775000, numberOfTreesEncountered)
    }
}