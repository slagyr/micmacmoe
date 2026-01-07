package com.micahmartin.micmacmoe

open class Game(val ui: UI,
                val board: Board,
                val playerX: Player,
                val playerO: Player) {
}

object NullGame : Game(NullUI, NullBoard, NullPlayer(Mark.X), NullPlayer(Mark.O))