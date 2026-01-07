package com.micahmartin.micmacmoe

enum class Mark {EMPTY, X, O}

abstract class Player(val mark: Mark) {
    abstract fun subTitle(): String
    abstract fun isAutoMover(): Boolean
    abstract fun makeMove(board:Board): Int

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

class EasyPlayer(mark: Mark) : Player(mark) {
    override fun subTitle(): String {
        return "Easy Computer"
    }

    override fun isAutoMover(): Boolean {
        return true
    }

    override fun makeMove(board: Board): Int {
        return board.emptyCells().shuffled().first()
    }
}

class MediumPlayer(mark: Mark) : Player(mark) {
    override fun subTitle(): String {
        return "Medium Computer"
    }

    override fun isAutoMover(): Boolean {
        return true
    }

    override fun makeMove(board: Board): Int {
        TODO("Not yet implemented")
    }
}

class UnbeatablePlayer(mark: Mark) : Player(mark) {
    override fun subTitle(): String {
        return "Unbeatable Computer"
    }

    override fun isAutoMover(): Boolean {
        return true
    }

    override fun makeMove(board: Board): Int {
        TODO("Not yet implemented")
    }
}