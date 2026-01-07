package com.micahmartin.micmacmoe

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.micahmartin.micmacmoe.ComposeUI.PlayerSelection
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.Before

class GameActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val ui = ComposeUI()

    @Before
    fun setUp() {
        Context.game = Game(ui, Board(), NullPlayer(Mark.X), NullPlayer(Mark.O))
        composeTestRule.setContent {
            GameScreen(NullActivity())
        }
    }

    @Test
    fun testEmptyBoard() {
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
        Context.game.board.mark(0, Mark.X)
        Context.game.board.mark(1, Mark.O)
        ui.update(Context.game)
        composeTestRule.onNodeWithTag("cell-0").assertTextEquals("X")
        composeTestRule.onNodeWithTag("cell-1").assertTextEquals("O")
        composeTestRule.onNodeWithTag("cell-2").assertTextEquals("")
    }
}

class NullActivity : ComponentActivity() {

}
