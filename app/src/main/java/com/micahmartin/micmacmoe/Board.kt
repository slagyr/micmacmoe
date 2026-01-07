package com.micahmartin.micmacmoe

enum class Mark { E, X, O } // E for EMPTY

open class Board {
    val cells: Array<Mark> = Array(9) { Mark.E }

    fun mark(cell: Int, mark: Mark) {
        require(cell in 0..8) { "Position must be between 0 and 8" }
        cells[cell] = mark
    }

    fun cell(cell: Int): Mark {
        return cells[cell]
    }

    companion object {
        private val winCombos = arrayOf(
            intArrayOf(0, 1, 2),  // Top row
            intArrayOf(3, 4, 5),  // Middle row
            intArrayOf(6, 7, 8),  // Bottom row
            intArrayOf(0, 3, 6),  // Left column
            intArrayOf(1, 4, 7),  // Middle column
            intArrayOf(2, 5, 8),  // Right column
            intArrayOf(0, 4, 8),  // Diagonal \
            intArrayOf(2, 4, 6)   // Diagonal /
        )
    }

    private fun isComboWin(mark: Mark, combo: IntArray): Boolean {
        return combo.all { cells[it] == mark }
    }

    fun isWin(mark: Mark): Boolean {
        return winCombos.any({combo -> isComboWin(mark, combo)})
    }


    fun isFull(): Boolean {
        return !cells.any({it == Mark.E})
    }

    fun isDraw(): Boolean {
        return isFull() && !isWin(Mark.X) && !isWin(Mark.O)
    }

    fun emptyCells(): List<Int> {
        return cells.indices.filter { cells[it] == Mark.E }
    }

    fun setup(c0: Mark, c1: Mark, c2: Mark, c3: Mark, c4: Mark, c5: Mark, c6: Mark, c7: Mark, c8: Mark) {
        cells[0] = c0
        cells[1] = c1
        cells[2] = c2
        cells[3] = c3
        cells[4] = c4
        cells[5] = c5
        cells[6] = c6
        cells[7] = c7
        cells[8] = c8
    }

    private fun cellString(cell: Int): String {
        val mark = cells[cell]
        return when (mark) {
            Mark.E -> " "
            Mark.X -> "X"
            Mark.O -> "O"
        }
    }

    override fun toString():String {
        // X | O | X
        // ---------
        // O | X | O
        // ---------
        // X | O | O
        return "${cellString(0)} | ${cellString(1)} | ${cellString(2)}\n" +
                "--------- \n" +
                "${cellString(3)} | ${cellString(4)} | ${cellString(5)}\n" +
                "--------- \n" +
                "${cellString(6)} | ${cellString(7)} | ${cellString(8)}"
    }
}