package com.micahmartin.micmacmoe

import kotlin.random.Random


abstract class Player(val mark: Mark) {
    abstract fun subTitle(): String
    abstract fun isAutoMover(): Boolean
    abstract fun makeMove(board: Board): Int

    fun title(): String {
        return subTitle() + " " + mark.name
    }
}

class NullPlayer(mark: Mark) : Player(mark) {
    override fun subTitle(): String {
        return "Null Player"
    }

    override fun isAutoMover(): Boolean {
        return false
    }

    override fun makeMove(board: Board): Int {
        return Context.game.ui.getMove(Context.game)
    }
}

class HumanPlayer(mark: Mark) : Player(mark) {
    override fun subTitle(): String {
        return "Human Player"
    }

    override fun isAutoMover(): Boolean {
        return false
    }

    override fun makeMove(board: Board): Int {
        return Context.game.ui.getMove(Context.game)
    }
}

abstract class ComputerPlayer(mark: Mark) : Player(mark) {

    override fun isAutoMover(): Boolean {
        return true
    }

    protected fun makeRandomMove(board: Board): Int {
        return board.emptyCells().shuffled().first()
    }


    protected fun makeBestMove(board: Board): Int {
        var bestScore = Int.MIN_VALUE
        var bestMove = -1

        for (cell in board.emptyCells().shuffled()) {
            board.mark(cell, mark)
            val score = minimax(board, 0, false)
            board.mark(cell, Mark.E)

            if (score > bestScore) {
                bestScore = score
                bestMove = cell
            }
        }

        return bestMove
    }

    protected fun minimax(board: Board, depth: Int, isMaximizing: Boolean): Int {
        if (board.isWin(mark)) return 10 - depth  // Prefer faster wins
        if (board.isWin(opponentMark())) return depth - 10  // Prefer slower losses
        if (board.isDraw()) return 0

        if (isMaximizing) {
            var bestScore = Int.MIN_VALUE
            for (cell in board.emptyCells().shuffled()) {
                board.mark(cell, mark)
                val score = minimax(board, depth + 1, false)
                board.mark(cell, Mark.E)
                bestScore = maxOf(bestScore, score)
            }
            return bestScore
        } else {
            var bestScore = Int.MAX_VALUE
            for (cell in board.emptyCells().shuffled()) {
                board.mark(cell, opponentMark())
                val score = minimax(board, depth + 1, true)
                board.mark(cell, Mark.E)
                bestScore = minOf(bestScore, score)
            }
            return bestScore
        }
    }

    protected fun opponentMark(): Mark = if (mark == Mark.X) Mark.O else Mark.X
}

class EasyPlayer(mark: Mark) : ComputerPlayer(mark) {
    override fun subTitle(): String {
        return "Easy Computer"
    }

    override fun makeMove(board: Board): Int {
        return makeRandomMove(board)
    }
}

class MediumPlayer(mark: Mark) : ComputerPlayer(mark) {
    override fun subTitle(): String {
        return "Medium Computer"
    }

    override fun makeMove(board: Board): Int {
        // 20% chance to make a random move
        if (Random.nextDouble() < 0.2) {
            return makeRandomMove(board)
        } else {
            return makeBestMove(board)
        }
    }
}

class UnbeatablePlayer(mark: Mark) : ComputerPlayer(mark) {
    override fun subTitle(): String {
        return "Unbeatable Computer"
    }

    override fun makeMove(board: Board): Int {
        return makeBestMove(board)
    }
}