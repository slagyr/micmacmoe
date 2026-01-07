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
}

object NullBoard : Board() {

}
