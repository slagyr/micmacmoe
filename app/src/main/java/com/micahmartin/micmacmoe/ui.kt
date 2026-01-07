package com.micahmartin.micmacmoe

interface UI {
    fun update(game: Game)
}

object NullUI : UI {
    override fun update(game: Game) {
    }
}