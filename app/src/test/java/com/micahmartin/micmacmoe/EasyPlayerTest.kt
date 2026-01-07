package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EasyPlayerTest {

    @Test
    fun title() {
        assertEquals("Easy Computer X", EasyPlayer(X).title())
        assertEquals("Easy Computer O", EasyPlayer(O).title())
    }

    @Test
    fun isAutoMover() {
        assertEquals(true, EasyPlayer(X).isAutoMover())
    }

    @Test
    fun makeMoveWithOnlyOneOption() {
        val board = Board()
        board.mark(0, X)
        board.mark(1, O)
        board.mark(2, X)
        board.mark(3, O)
        board.mark(4, X)
        board.mark(5, O)
        board.mark(6, O)
        board.mark(7, X)

        assertEquals(8, EasyPlayer(O).makeMove(board))
    }

    @Test
    fun makeMoveRandomly() {
        val board = Board()
        val moves = mutableListOf<Int>()
        repeat(100) {moves.add(EasyPlayer(X).makeMove(board))}
//        println("moves = ${moves}")
        // We could mock our randomness here but we're using language features so a simple check of
        //  randomness that *almost" never fails should do.
        assertTrue(moves.toSet().size > 1)
    }
}