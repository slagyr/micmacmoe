package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Test

class MediumPlayerTest {

    @Test
    fun title() {
        assertEquals("Medium Computer X", MediumPlayer(X).title())
        assertEquals("Medium Computer O", MediumPlayer(O).title())
    }

    @Test
    fun isAutoMover() {
        assertEquals(true, MediumPlayer(X).isAutoMover())
    }

}