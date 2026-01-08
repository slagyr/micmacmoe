package com.micahmartin.micmacmoe

class MockPlayer(mark: Mark) : Player(mark) {

    val moves = mutableListOf<Int>()

    override fun subTitle(): String {
        return "Mock Player"
    }

    override fun isAutoMover(): Boolean {
        return false
    }

    override fun makeMove(board: Board): Int {
        val move = moves.first()
        moves.removeFirst()
        return move
    }
}