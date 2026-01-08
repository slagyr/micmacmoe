package com.micahmartin.micmacmoe

class MockUI : UI {
    var lastUpdate: Game? = null

    override fun update(game: Game) {
        lastUpdate = game
    }

    override fun getMove(game: Game): Int {
        TODO("Not yet implemented")
    }
}