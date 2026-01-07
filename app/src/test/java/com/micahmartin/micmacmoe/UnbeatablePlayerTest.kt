package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.E
import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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

    @Test
    fun takesFastestWin() {
        board.setup(
            X, O, X,
            X, E, O,
            E, E, O
        )
        assertEquals(6, player.makeMove(board))
    }

    @Test
    fun preventsLoss() {
        board.setup(
            X, O, X,
            E, O, E,
            E, E, E
        )
        assertEquals(7, player.makeMove(board))
    }

    @Test
    fun takesWinOverPreventingLoss() {
        board.setup(
            X, O, E,
            X, O, E,
            E, E, E
        )
        assertEquals(6, player.makeMove(board))
    }

    @Test
    fun randomlyChoosesAmongBestMoves() {
        // Consider commenting as this is a relatively slow test.
        board.mark(4, O)
        val moves = mutableListOf<Int>()
        repeat(100) { moves.add(player.makeMove(board)) }
        assertEquals(setOf(0, 2, 6, 8), moves.toSet()) // corner required
    }

    fun assertPlayerDoesNotLose(board: Board, player: Player, opponent: Mark) {
        val startingBoard = board.toString()
        for (cell in board.emptyCells()) {
            board.mark(cell, opponent)
            assertFalse(
                "$startingBoard\n${opponent.name} moves in $cell and should not win. \n$board",
                board.isWin(opponent)
            )
            if(!board.isFull()) {
                val move = player.makeMove(board)
                board.mark(move, player.mark)
                if(!board.isWin(player.mark))
                    assertPlayerDoesNotLose(board, player, opponent)
                board.mark(move, E)
            }
            board.mark(cell, E)
        }
    }

    @Test
    fun neverLosesAsX() {
        for (cell in board.emptyCells()) {
            board.mark(cell, X)
            println(board)
            assertPlayerDoesNotLose(board, player, O)
            board.mark(cell, E)
        }
    }

    @Test
    fun neverLosesAsO() {
        assertPlayerDoesNotLose(board, UnbeatablePlayer(O), X)
    }
}