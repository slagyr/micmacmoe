package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.E
import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MediumPlayerTest {

    val player = MediumPlayer(X)
    val board = Board()

    @Test
    fun title() {
        assertEquals("Medium Computer X", MediumPlayer(X).title())
        assertEquals("Medium Computer O", MediumPlayer(O).title())
    }

    @Test
    fun isAutoMover() {
        assertEquals(true, player.isAutoMover())
    }

    @Test
    fun usuallyMakesBestMove() {
        board.setup(
            X, O, E,
            X, O, E,
            E, E, E
        )
        val moves = mutableListOf<Int>()
        repeat(100) {moves.add(player.makeMove(board))}
        println(moves)
        val bestMoves = moves.count { it == 6 }
        assertTrue(bestMoves > 50)
    }

}