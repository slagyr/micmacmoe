package com.micahmartin.micmacmoe

import com.micahmartin.micmacmoe.ComposeUI.Companion.createGame
import com.micahmartin.micmacmoe.ComposeUI.Companion.selectionToPlayer
import com.micahmartin.micmacmoe.ComposeUI.PlayerSelection
import org.junit.Assert.assertEquals
import org.junit.Test

class ComposeUITest {

    @Test
    fun selectionToHuman() {
        val player = selectionToPlayer(PlayerSelection.Human, Mark.X)
        assertEquals(HumanPlayer::class, player.javaClass.kotlin)
        assertEquals(Mark.X, player.mark)
    }

    @Test
    fun selectionToEasy() {
        val player = selectionToPlayer(PlayerSelection.Easy, Mark.X)
        assertEquals(EasyPlayer::class, player.javaClass.kotlin)
        assertEquals(Mark.X, player.mark)
    }

    @Test
    fun selectionToMedium() {
        val player = selectionToPlayer(PlayerSelection.Medium, Mark.X)
        assertEquals(MediumPlayer::class, player.javaClass.kotlin)
        assertEquals(Mark.X, player.mark)
    }

    @Test
    fun selectionToUnbeatable() {
        val player = selectionToPlayer(PlayerSelection.Unbeatable, Mark.X)
        assertEquals(UnbeatablePlayer::class, player.javaClass.kotlin)
        assertEquals(Mark.X, player.mark)
    }

    @Test
    fun gameCreation() {
        createGame(PlayerSelection.Human, PlayerSelection.Unbeatable)

        assertEquals(ComposeUI::class, Context.game.ui.javaClass.kotlin)
        assertEquals(HumanPlayer::class, Context.game.playerX.javaClass.kotlin)
        assertEquals(UnbeatablePlayer::class, Context.game.playerO.javaClass.kotlin)
        assertEquals(Mark.X, Context.game.playerX.mark)
        assertEquals(Mark.O, Context.game.playerO.mark)
    }

}