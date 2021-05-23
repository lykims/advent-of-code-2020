import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day11Test {

    private val operation: Day11 = Day11()

    @Test
    fun test() {
        // Given
        val input = listOf(
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL"
        )

        // When
        val occupiedSeatCount = operation.getAdjacentOccupiedSeatCount(input)

        // Then
        assertEquals(37, occupiedSeatCount)
    }

    @Test
    fun test2() {
        // Given
        val input = File(Day11Test::class.java.getResource("day-11-input.txt").file)
            .readLines()

        // When
        val occupiedSeatCount = operation.getAdjacentOccupiedSeatCount(input)

        // Then
        assertEquals(2441, occupiedSeatCount)
    }

    @Test
    fun test3() {
        // Given
        val input = listOf(
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL"
        )

        // When
        val occupiedSeatCount = operation.getVisibleOccupiedSeatCount(input)

        // Then
        assertEquals(26, occupiedSeatCount)
    }

    @Test
    fun test4() {
        // Given
        val input = File(Day11Test::class.java.getResource("day-11-input.txt").file)
            .readLines()

        // When
        val occupiedSeatCount = operation.getVisibleOccupiedSeatCount(input)

        // Then
        assertEquals(2190, occupiedSeatCount)
    }

    @Test
    fun testVisibleOccupiedSeatCount() {
        // Given
        val input = listOf(
            ".......#.",
            "...#.....",
            ".#.......",
            ".........",
            "..#L....#",
            "....#....",
            ".........",
            "#........",
            "...#....."
        )
        val layout = operation.toLayoutArray(input)

        // When
        val visibleOccupiedSeatCount = operation.getVisibleOccupiedSeatCount(layout, SpacePoint(3, 4))

        // Then
        assertEquals(8, visibleOccupiedSeatCount)
    }

    @Test
    fun testVisibleOccupiedSeatCount2() {
        // Given
        val input = listOf(
            ".............",
            ".L.L.#.#.#.#.",
            "............."
        )
        val layout = operation.toLayoutArray(input)

        // When
        val visibleOccupiedSeatCount = operation.getVisibleOccupiedSeatCount(layout, SpacePoint(2, 1))

        // Then
        assertEquals(0, visibleOccupiedSeatCount)
    }

    @Test
    fun testVisibleOccupiedSeatCount3() {
        // Given
        val input = listOf(
            ".##.##.",
            "#.#.#.#",
            "##...##",
            "...L...",
            "##...##",
            "#.#.#.#",
            ".##.##."
        )
        val layout = operation.toLayoutArray(input)

        // When
        val visibleOccupiedSeatCount = operation.getVisibleOccupiedSeatCount(layout, SpacePoint(3, 3))

        // Then
        assertEquals(0, visibleOccupiedSeatCount)
    }
}
