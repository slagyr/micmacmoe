package com.micahmartin.micmacmoe

open class Game(val board: Board, val playerX: Player, val playerO: Player) {
}

object NullGame : Game(NullBoard, NullPlayer(Mark.X), NullPlayer(Mark.O))