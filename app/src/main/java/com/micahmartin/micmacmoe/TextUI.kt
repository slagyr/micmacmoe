package com.micahmartin.micmacmoe

import java.util.Scanner

class TextUI : UI {
    override fun update(game: Game) {
        println(game.status())
        println()
        println(boardString(game.board))
    }

    private fun cellString(board: Board, cell: Int): String {
        val mark = board.cell(cell)
        return when (mark) {
            Mark.E -> (cell + 1).toString()
            Mark.X -> "X"
            Mark.O -> "O"
        }
    }

    fun boardString(board: Board): String {
        return "${cellString(board, 0)} | ${cellString(board, 1)} | ${cellString(board, 2)}\n" +
                "--------- \n" +
                "${cellString(board, 3)} | ${cellString(board, 4)} | ${cellString(board, 5)}\n" +
                "--------- \n" +
                "${cellString(board, 6)} | ${cellString(board, 7)} | ${cellString(board, 8)}\n"
    }

    override fun getMove(game: Game): Int {
        val scanner = Scanner(System.`in`)
        var move = -1
        while (move < 0) {
            print("move? ")
            val line = scanner.nextLine()
            try {
                val cell = line.toInt() - 1
                if(game.board.cell(cell) != Mark.E) {
                    println("Move taken")
                } else {
                    move = cell
                }
            } catch (e: Exception) {
                println("Invalid move")
                move = -1
            }
        }
        return move
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val textUI = TextUI()
            Context.game = Game(textUI, Board(), HumanPlayer(Mark.X), UnbeatablePlayer(Mark.O))
            Context.game.play()
        }
    }
}