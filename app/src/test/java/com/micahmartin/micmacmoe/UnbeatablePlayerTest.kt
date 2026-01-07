package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.E
import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Test


class UnbeatablePlayerTest {

    var board = Board()
    var player = UnbeatablePlayer(X)

    @Test
    fun title() {
        assertEquals("Unbeatable Computer X", UnbeatablePlayer(X).title())
        assertEquals("Unbeatable Computer O", UnbeatablePlayer(O).title())
    }

    @Test
    fun isAutoMover() {
        assertEquals(true, UnbeatablePlayer(X).isAutoMover())
    }

    @Test
    fun takesWinWithOneMoveLeft() {
        board.setup(
            X, O, X,
            O, X, O,
            O, X, E
        )
        assertEquals(8, player.makeMove(board))
    }

//    @Test
//    fun takesWinNotOnlyMove() {
//        board.setup(
//            X, O, X,
//            X, E, O,
//            E, E, O
//        )
//        assertEquals(6, player.makeMove(board))
//    }
}