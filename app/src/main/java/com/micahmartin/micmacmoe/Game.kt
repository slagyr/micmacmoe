package com.micahmartin.micmacmoe

open class Game(
    val ui: UI,
    val board: Board,
    val playerX: Player,
    val playerO: Player
) {

    var currentPlayer: Player = playerX

    fun isOver(): Boolean {
        return board.isWin(Mark.X) || board.isWin(Mark.O) || board.isDraw()
    }

    fun status(): String {
        return if(board.isWin(Mark.X)) {
            "${playerX.title()} WINS!"
        } else if(board.isWin(Mark.O)) {
            "${playerO.title()} WINS!"
        } else if(board.isDraw()) {
            "Cats Game!"
        } else {
            "${currentPlayer.title()}'s move"
        }
    }

    fun playMove() {
        val move = currentPlayer.makeMove(board)
        println("move = ${move}")
        playMove(move)
    }

    fun playMove(move: Int) {
        if(!isOver()) {
            board.mark(move, currentPlayer.mark)
            currentPlayer = if (currentPlayer == playerX) playerO else playerX
            ui.update(this)
        }
    }

    fun play() {
        ui.update(this)
        while(!isOver()) {
            playMove()
        }
    }
}

object NullGame : Game(NullUI, Board(), NullPlayer(Mark.X), NullPlayer(Mark.O))