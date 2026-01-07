package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Test

class NullPlayerTest {

    @Test
    fun title() {
        assertEquals("Null Player X", NullPlayer(X).title())
        assertEquals("Null Player O", NullPlayer(O).title())
    }

    @Test
    fun isAutoMover() {
        assertEquals(false, NullPlayer(X).isAutoMover())
    }

}