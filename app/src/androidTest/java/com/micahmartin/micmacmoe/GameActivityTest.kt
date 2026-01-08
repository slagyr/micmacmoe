package com.micahmartin.micmacmoe

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.micahmartin.micmacmoe.Mark.E
import com.micahmartin.micmacmoe.Mark.O
import com.micahmartin.micmacmoe.Mark.X
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GameActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val ui = ComposeUI()
    val board = Board()
    var game = Game(ui, board, NullPlayer(X), NullPlayer(O))

    fun loadScreen() {
        Context.game = game
        composeTestRule.setContent {
            GameScreen(NullActivity())
        }
    }

    @Test
    fun testEmptyBoard() {
        loadScreen()
        composeTestRule.onNodeWithTag("cell-0").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-1").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-2").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-3").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-4").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-5").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-6").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-7").assertTextEquals("")
        composeTestRule.onNodeWithTag("cell-8").assertTextEquals("")
    }

    @Test
    fun testMarkedBoard() {
        board.mark(0, X)
        board.mark(1, O)
        loadScreen()
        ui.update(Context.game)
        composeTestRule.onNodeWithTag("cell-0").assertTextEquals("X")
        composeTestRule.onNodeWithTag("cell-1").assertTextEquals("O")
        composeTestRule.onNodeWithTag("cell-2").assertTextEquals("")
    }

    @Test
    fun cantMoveWhenGameIsOver() {
        board.setup(
            X, O, E,
            X, O, E,
            X, E, E
        )
        loadScreen()
        ui.update(Context.game)
        composeTestRule.onNodeWithTag("cell-7").performClick()
        assertEquals(E, Context.game.board.cell(7))
    }

    @Test
    fun playAgain() {
        board.setup(
            X, O, E,
            X, O, E,
            X, E, E
        )
        loadScreen()
        ui.update(Context.game)
        composeTestRule.onNodeWithText("Play Again").performClick()
        assertEquals(9, Context.game.board.emptyCells().count())
    }

    @Test
    fun twoComputersCompleteTheGame() {
        game = Game(ui, board, EasyPlayer(X), EasyPlayer(O))
        loadScreen()
        moveDelay = 1
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithText("Play Again").isDisplayed()
        }
        assertEquals(true, game.isOver())
    }
}

class NullActivity : ComponentActivity() {

}
