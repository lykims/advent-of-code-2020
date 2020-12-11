// Day 5: Binary Boarding
class Day5 {

    fun getHighestSeatId(
        input: List<String>
    ): Int {
        return input.map { decodeBoardingPass(it) }.maxOrNull() ?: 0
    }

    fun findMySeat(
        input: List<String>
    ): Int {
        val seats = input.map { decodeBoardingPass(it) }
        return (1..128 * 8).first { !seats.contains(it) && seats.contains(it + 1) && seats.contains(it - 1) }
    }

    fun decodeBoardingPass(
        boardingPass: String
    ): Int {
        val row = decode(boardingPass, Cursor(0, 'F'), Cursor(127, 'B'), 0, 5)
        val seat = decode(boardingPass, Cursor(0, 'L'), Cursor(7, 'R'), 7, 8)
        return row * 8 + seat
    }

    private fun decode(
        boardingPass: String,
        startCursor: Cursor,
        endCursor: Cursor,
        startIndex: Int,
        endIndex: Int
    ): Int {
        for (i in startIndex..endIndex) {
            val letter = boardingPass[i]
            if (letter == startCursor.letter) {
                endCursor.index -= (endCursor.index - startCursor.index + 1) / 2
            } else if (letter == endCursor.letter) {
                startCursor.index += (endCursor.index - startCursor.index + 1) / 2
            }
        }
        return if (boardingPass[endIndex + 1] == startCursor.letter) startCursor.index else endCursor.index
    }
}

private data class Cursor(
    var index: Int,
    val letter: Char
)