package com.micahmartin.micmacmoe

enum class Mark {EMPTY, X, O}

abstract class Player(val mark: Mark) {
}

class NullPlayer(mark: Mark) : Player(mark)

class HumanPlayer(mark: Mark) : Player(mark)

class EasyPlayer(mark: Mark) : Player(mark)

class MediumPlayer(mark: Mark) : Player(mark)

class UnbeatablePlayer(mark: Mark) : Player(mark)