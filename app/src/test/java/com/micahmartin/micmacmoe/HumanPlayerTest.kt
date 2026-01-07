package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Test

class HumanPlayerTest {

    @Test
    fun title() {
        assertEquals("Human Player X", HumanPlayer(X).title())
        assertEquals("Human Player O", HumanPlayer(O).title())
    }

    @Test
    fun isAutoMover() {
        assertEquals(false, HumanPlayer(X).isAutoMover())
    }

}