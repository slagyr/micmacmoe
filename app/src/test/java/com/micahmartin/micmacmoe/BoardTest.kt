package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.EMPTY
import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.Before

class BoardTest {

    val board: Board = Board()

    @Before
    fun setup() {
    }

    @Test
    fun empty() {
        assertEquals(EMPTY, board.cell(0))
        assertEquals(EMPTY, board.cell(1))
        assertEquals(EMPTY, board.cell(2))
        assertEquals(EMPTY, board.cell(3))
        assertEquals(EMPTY, board.cell(4))
        assertEquals(EMPTY, board.cell(5))
        assertEquals(EMPTY, board.cell(6))
        assertEquals(EMPTY, board.cell(7))
        assertEquals(EMPTY, board.cell(8))
    }

    @Test
    fun placeMark() {
        board.mark(5, X)
        board.mark(8, O)
        assertEquals(X, board.cell(5))
        assertEquals(O, board.cell(8))
    }

    @Test
    fun winTopRow() {
        assertEquals(false, board.isWin(X))
        board.mark(0, X)
        board.mark(1, X)
        board.mark(2, X)
        assertEquals(true, board.isWin(X))
    }

    @Test
    fun winMiddleRow() {
        assertEquals(false, board.isWin(O))
        board.mark(3, O)
        board.mark(4, O)
        board.mark(5, O)
        assertEquals(true, board.isWin(O))
    }

    @Test
    fun winBottomRow() {
        assertEquals(false, board.isWin(X))
        board.mark(6, X)
        board.mark(7, X)
        board.mark(8, X)
        assertEquals(true, board.isWin(X))
    }

    @Test
    fun winLeftCol() {
        assertEquals(false, board.isWin(O))
        board.mark(0, O)
        board.mark(3, O)
        board.mark(6, O)
        assertEquals(true, board.isWin(O))
    }

    @Test
    fun winMiddleCol() {
        assertEquals(false, board.isWin(X))
        board.mark(1, X)
        board.mark(4, X)
        board.mark(7, X)
        assertEquals(true, board.isWin(X))
    }

    @Test
    fun winRightCol() {
        assertEquals(false, board.isWin(O))
        board.mark(2, O)
        board.mark(5, O)
        board.mark(8, O)
        assertEquals(true, board.isWin(O))
    }

    @Test
    fun winDiagonalTopLeft() {
        assertEquals(false, board.isWin(X))
        board.mark(0, X)
        board.mark(4, X)
        board.mark(8, X)
        assertEquals(true, board.isWin(X))
    }

    @Test
    fun winDiagonalBottomLeft() {
        assertEquals(false, board.isWin(O))
        board.mark(2, O)
        board.mark(4, O)
        board.mark(6, O)
        assertEquals(true, board.isWin(O))
    }


    @Test
    fun isDraw() {
        assertEquals(false, board.isFull())
        assertEquals(false, board.isDraw())
        fillBoard()
        assertEquals(true, board.isFull())
        assertEquals(true, board.isDraw())
    }

    private fun fillBoard() {
//        X O X
//        O X O
//        O X O
        board.mark(0, X)
        board.mark(1, O)
        board.mark(2, X)
        board.mark(3, O)
        board.mark(4, X)
        board.mark(5, O)
        board.mark(6, O)
        board.mark(7, X)
        board.mark(8, O)
    }

    @Test
    fun notDrawWhenXWins() {
        fillBoard()
        board.mark(8, X)
        assertEquals(false, board.isDraw())
    }

    @Test
    fun notDrawWhenOWins() {
        fillBoard()
        board.mark(7, O)
        assertEquals(false, board.isDraw())
    }

    @Test
    fun emptyCells() {
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8), board.emptyCells())

        board.mark(0, X)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8), board.emptyCells())

        board.mark(8, O)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7), board.emptyCells())
    }
}