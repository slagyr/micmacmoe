package com.micahmartin.micmacmoe

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.Before

class GameActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    var selectedPlayerOne: PlayerSelection? = null
    var selectedPlayerTwo: PlayerSelection? = null

    @Before
    fun setUp() {
        composeTestRule.setContent {
            GameScreen(NullActivity(), PlayerSelection.Human, PlayerSelection.Human)
        }
    }

    @Test
    fun testDefaultPlayerSelections() {
        composeTestRule.onNodeWithText("Play").performClick()

        assertEquals(PlayerSelection.Human, selectedPlayerOne)
        assertEquals(PlayerSelection.Unbeatable, selectedPlayerTwo)
    }
}

class NullActivity : ComponentActivity() {

}
