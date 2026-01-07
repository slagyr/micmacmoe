package com.micahmartin.micmacmoe


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
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }
}

abstract class ComputerPlayer(mark: Mark) : Player(mark) {

    override fun isAutoMover(): Boolean {
        return true
    }

    protected fun makeRandomMove(board: Board): Int {
        return board.emptyCells().shuffled().first()
    }
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
        TODO("Not yet implemented")
    }
}

class UnbeatablePlayer(mark: Mark) : ComputerPlayer(mark) {
    override fun subTitle(): String {
        return "Unbeatable Computer"
    }

    override fun makeMove(board: Board): Int {
        return makeRandomMove(board)
    }
}