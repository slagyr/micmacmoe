package com.micahmartin.micmacmoe

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.Before

class HomeActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    var selectedPlayerOne: PlayerSelection? = null
    var selectedPlayerTwo: PlayerSelection? = null

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(onPlayClicked = { player1, player2 ->
                selectedPlayerOne = player1
                selectedPlayerTwo = player2
            })
        }
    }

    @Test
    fun testDefaultPlayerSelections() {
        composeTestRule.onNodeWithText("Play").performClick()

        assertEquals(PlayerSelection.Human, selectedPlayerOne)
        assertEquals(PlayerSelection.Unbeatable, selectedPlayerTwo)
    }

    @Test
    fun testAlternatePlayerSelections() {
        composeTestRule.onNodeWithTag("player_One_selector_Medium").performClick()
        composeTestRule.onNodeWithTag("player_Two_selector_Easy").performClick()

        composeTestRule.onNodeWithText("Play").performClick()

        assertEquals(PlayerSelection.Medium, selectedPlayerOne)
        assertEquals(PlayerSelection.Easy, selectedPlayerTwo)
    }

}