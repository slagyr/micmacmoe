package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Test

class UnbeatablePlayerTest {

    @Test
    fun title() {
        assertEquals("Unbeatable Computer X", UnbeatablePlayer(X).title())
        assertEquals("Unbeatable Computer O", UnbeatablePlayer(O).title())
    }

    @Test
    fun isAutoMover() {
        assertEquals(true, UnbeatablePlayer(X).isAutoMover())
    }

}