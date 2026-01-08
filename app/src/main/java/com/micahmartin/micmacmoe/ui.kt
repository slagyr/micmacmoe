package com.micahmartin.micmacmoe

interface UI {
    fun update(game: Game)
    fun getMove(game: Game): Int
}

object NullUI : UI {
    override fun update(game: Game) {
    }

    override fun getMove(game: Game):Int {
        return 0
    }
}