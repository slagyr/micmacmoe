package com.micahmartin.micmacmoe


class ComposeUI {
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
            Context.game = Game(NullBoard, playerX, playerO)
        }
    }
}