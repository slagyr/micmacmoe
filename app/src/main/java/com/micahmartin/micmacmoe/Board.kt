package com.micahmartin.micmacmoe

open class Board {
    val cells: Array<Mark> = Array(9) { Mark.EMPTY }

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
        return !cells.any({it == Mark.EMPTY})
    }

    fun isDraw(): Boolean {
        return isFull() && !isWin(Mark.X) && !isWin(Mark.O)
    }
}