package com.micahmartin.micmacmoe

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.ByteArrayInputStream
import com.micahmartin.micmacmoe.Mark.X
import com.micahmartin.micmacmoe.Mark.O
import org.junit.Assert.assertEquals
import org.junit.Test

inline fun captureOutput(block: () -> Unit): String {
    val originalOut = System.out
    val byteArrayOutputStream = ByteArrayOutputStream()
    val printStream = PrintStream(byteArrayOutputStream, true, Charsets.UTF_8)

    return try {
        System.setOut(printStream)
        block()
        printStream.flush()
        byteArrayOutputStream.toString(Charsets.UTF_8)
    } finally {
        System.setOut(originalOut)
        printStream.close()
        byteArrayOutputStream.close()
    }
}

inline fun withInput(input: String, block: () -> Unit) {
    val originalIn = System.`in`
    val inputStream = ByteArrayInputStream(input.toByteArray(Charsets.UTF_8))

    try {
        System.setIn(inputStream)
        block()
    } finally {
        System.setIn(originalIn)
        inputStream.close()
    }
}


class TextUITest {

    val ui = TextUI()
    val board = Board()
    val playerX = MockPlayer(X)
    val playerO = MockPlayer(O)
    val game = Game(ui, board, playerX, playerO)

    @Test
    fun updateAtStart() {
        val output = captureOutput { ui.update(game) }
        assertEquals("Mock Player X's move\n" +
                "\n" +
                "1 | 2 | 3\n" +
                "--------- \n" +
                "4 | 5 | 6\n" +
                "--------- \n" +
                "7 | 8 | 9\n\n", output)
    }

    @Test
    fun getMove() {
        withInput("5\n") {
            val output = captureOutput {
                assertEquals(4, ui.getMove(game))
            }
            assertEquals("move? ", output)
        }
    }

    @Test
    fun getMoveBadInput() {
        withInput("Blah\n1\n") {
            val output = captureOutput {
                assertEquals(0, ui.getMove(game))
            }
            assertEquals("move? Invalid move\nmove? ", output)
        }
    }

    @Test
    fun getMoveCellTaken() {
        board.mark(0, X)
        withInput("1\n9\n") {
            val output = captureOutput {
                assertEquals(8, ui.getMove(game))
            }
            assertEquals("move? Move taken\nmove? ", output)
        }
    }

}