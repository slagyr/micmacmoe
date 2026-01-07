package com.micahmartin.micmacmoe

import androidx.compose.runtime.mutableStateListOf

class ComposeUI : UI {
    enum class PlayerSelection {
        Human, Easy, Medium, Unbeatable
    }

    companion object {
        fun selectionToPlayer(selection: PlayerSelection, mark: Mark): Player {
            return when (selection) {
                PlayerSelection.Human -> HumanPlayer(mark)
                PlayerSelection.Easy -> EasyPlayer(mark)
                PlayerSelection.Medium -> MediumPlayer(mark)
                PlayerSelection.Unbeatable -> UnbeatablePlayer(mark)
            }
        }

        fun createGame(xSelection: PlayerSelection, oSelection: PlayerSelection) {
            val playerX = selectionToPlayer(xSelection, Mark.X)
            val playerO = selectionToPlayer(oSelection, Mark.O)
            Context.game = Game(ComposeUI(), Board(), playerX, playerO)
        }
    }

    val boardState = mutableStateListOf("", "", "", "", "", "", "", "", "")
    override fun update(game: Game) {
        for (i in 0..8) {
            boardState[i] = markToString(game.board.cell(i))
        }
    }

    fun markToString(mark: Mark):String {
        return when(mark) {
            Mark.X -> "X"
            Mark.O -> "O"
            Mark.E -> ""
        }
    }
}