package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.E
import com.micahmartin.micmacmoe.Mark.X
import com.micahmartin.micmacmoe.Mark.O
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class GameTest {

    val board = Board()
    val playerX = MockPlayer(X)
    val playerO = MockPlayer(O)
    val ui = MockUI()
    val game = Game(ui, board, playerX, playerO)

    @Test
    fun initialState() {
        assertEquals("Mock Player X's move", game.status())
    }

    @Test
    fun gameOverXWins() {
        assertEquals(false, game.isOver())
        board.setup(
            X, O, E,
            X, O, E,
            X, E, E
        )
        assertEquals(true, game.isOver())
        assertEquals("Mock Player X WINS!", game.status())
    }

    @Test
    fun gameOverOWins() {
        assertEquals(false, game.isOver())
        board.setup(
            X, O, E,
            X, O, E,
            E, O, E
        )
        assertEquals(true, game.isOver())
        assertEquals("Mock Player O WINS!", game.status())
    }

    @Test
    fun gameOverDraw() {
        assertEquals(false, game.isOver())
        board.setup(
            X, O, X,
            X, O, O,
            O, X, X
        )
        assertEquals(true, game.isOver())
        assertEquals("Cats Game!", game.status())
    }

    @Test
    fun playMove() {
        playerX.moves.add(4)
        game.playMove()

        assertEquals(X, board.cell(4))
        assertNotNull(ui.lastUpdate)
        assertEquals(playerO, game.currentPlayer)
        assertEquals("Mock Player O's move", game.status())
    }

    @Test
    fun playFullGame() {
        playerX.moves.add(0)
        playerO.moves.add(1)
        playerX.moves.add(3)
        playerO.moves.add(4)
        playerX.moves.add(6)

        game.play()

        assertEquals(true, game.isOver())
        assertEquals(X, board.cell(0))
        assertEquals(O, board.cell(1))
        assertEquals(X, board.cell(3))
        assertEquals(O, board.cell(4))
        assertEquals(X, board.cell(6))
        assertEquals("Mock Player X WINS!", game.status())
        assertNotNull(ui.lastUpdate)
    }
}
