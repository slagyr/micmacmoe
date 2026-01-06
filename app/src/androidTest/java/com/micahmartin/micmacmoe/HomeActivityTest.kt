package com.micahmartin.micmacmoe

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    var selectedPlayerOne: PlayerSelection? = null
    var selectedPlayerTwo: PlayerSelection? = null

    @Test
    fun testPlayButtonWithSelectedDifficulties() {
        println("selectedPlayerOne = ${selectedPlayerOne}")
        System.err.println("selectedPlayerOne = ${selectedPlayerOne}")
        composeTestRule.setContent {
            HomeScreen(onPlayClicked = { player1, player2 ->
                selectedPlayerOne = player1
                selectedPlayerTwo = player2
            })
        }

        composeTestRule.onNodeWithTag("player_One_selector_Medium").performClick()
        composeTestRule.onNodeWithTag("player_Two_selector_Unbeatable").performClick()
        composeTestRule.onNodeWithText("Play").performClick()

        assertEquals(PlayerSelection.Medium, selectedPlayerOne)
        assertEquals(PlayerSelection.Unbeatable, selectedPlayerTwo)
    }
}