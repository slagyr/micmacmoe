package com.micahmartin.micmacmoe

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.micahmartin.micmacmoe.ComposeUI.PlayerSelection
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.Before

class HomeActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    var selectedPlayerX: PlayerSelection? = null
    var selectedPlayerO: PlayerSelection? = null

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(onPlayClicked = { player1, player2 ->
                selectedPlayerX = player1
                selectedPlayerO = player2
            })
        }
    }

    @Test
    fun testDefaultPlayerSelections() {
        composeTestRule.onNodeWithText("Play").performClick()

        assertEquals(PlayerSelection.Human, selectedPlayerX)
        assertEquals(PlayerSelection.Unbeatable, selectedPlayerO)
    }

    @Test
    fun testAlternatePlayerSelections() {
        composeTestRule.onNodeWithTag("player_X_selector_Medium").performClick()
        composeTestRule.onNodeWithTag("player_O_selector_Easy").performClick()

        composeTestRule.onNodeWithText("Play").performClick()

        assertEquals(PlayerSelection.Medium, selectedPlayerX)
        assertEquals(PlayerSelection.Easy, selectedPlayerO)
    }

}