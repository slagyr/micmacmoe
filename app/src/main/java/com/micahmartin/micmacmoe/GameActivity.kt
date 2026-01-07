package com.micahmartin.micmacmoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micahmartin.micmacmoe.ui.theme.AccentPurple
import com.micahmartin.micmacmoe.ui.theme.ElectricCyan
import com.micahmartin.micmacmoe.ui.theme.HotMagenta
import com.micahmartin.micmacmoe.ui.theme.MicMacMoeTheme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val player1 =
            intent.getSerializableExtra("player1") as? PlayerSelection ?: PlayerSelection.Human
        val player2 =
            intent.getSerializableExtra("player2") as? PlayerSelection ?: PlayerSelection.Unbeatable
        setContent {
            MicMacMoeTheme(dynamicColor = false) {
                GameScreen(this, player1, player2)
            }
        }
    }
}

@Composable
fun GameScreen(activity: ComponentActivity, player1: PlayerSelection, player2: PlayerSelection) {
    val board = remember {
        mutableStateListOf(
            mutableStateListOf<String?>(null, null, null),
            mutableStateListOf<String?>(null, null, null),
            mutableStateListOf<String?>(null, null, null)
        )
    }
    var currentPlayer by remember { mutableStateOf("X") }
    var gameStatus by remember { mutableStateOf("Player X's turn") }
    var gameOver by remember { mutableStateOf(false) }

    // Function to make a move
    fun makeMove(row: Int, col: Int) {
        if (board[row][col] != null || gameOver) return
        board[row][col] = currentPlayer
        if (checkWin(board, currentPlayer)) {
            gameStatus = "Player $currentPlayer wins!"
            gameOver = true
        } else if (board.flatten().none { it == null }) {
            gameStatus = "It's a draw!"
            gameOver = true
        } else {
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            gameStatus = "Player $currentPlayer's turn"
            // Check if next player is AI
            if ((currentPlayer == "X" && player1 != PlayerSelection.Human) ||
                (currentPlayer == "O" && player2 != PlayerSelection.Human)
            ) {
                // Make AI move
                val aiMove =
                    getAIMove(board, currentPlayer, if (currentPlayer == "X") player1 else player2)
                aiMove?.let { makeMove(it.first, it.second) }
            }
        }
    }

    // Initial AI move if player1 is AI and starts
    LaunchedEffect(Unit) {
        if (player1 != PlayerSelection.Human) {
            val aiMove = getAIMove(board, "X", player1)
            aiMove?.let { makeMove(it.first, it.second) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0A0015), Color(0xFF1A0033))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Text(
                text = gameStatus,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 64.dp)
            )
            TicTacToeGrid(board) { row, col -> makeMove(row, col) }
            Spacer(modifier = Modifier.height(64.dp))
            if (gameOver) {
                NeonButton(text = "Play Again", onClick = { activity.finish() })
                Spacer(modifier = Modifier.height(32.dp))
                NeonButton(text = "Back", onClick = { activity.finish() })
            }
        }
    }
}

@Composable
fun TicTacToeGrid(board: List<List<String?>>, onCellClick: (Int, Int) -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            for (row in 0..2) {
                Row {
                    for (col in 0..2) {
                        TicTacToeCell(
                            value = board[row][col],
                            onClick = { onCellClick(row, col) }
                        )
                        if (col < 2) Spacer(
                            modifier = Modifier
                                .width(12.dp)
                                .height(84.dp)
                                .background(AccentPurple)
                                .glowEffect(AccentPurple)
                        )
                    }
                }
                if (row < 2) Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .width(256.dp)
                        .background(AccentPurple)
                        .glowEffect(AccentPurple)
                )
            }
        }
    }
}

@Composable
fun TicTacToeCell(value: String?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
//            .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
//            .glowEffect(if (value == "X") HotMagenta else if (value == "O") ElectricCyan else Color.Transparent)
            .clickable(enabled = value == null, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value ?: "",
            color = if (value == "X") HotMagenta else ElectricCyan,
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun checkWin(board: List<List<String?>>, player: String): Boolean {
    // Check rows
    for (row in board) {
        if (row.all { it == player }) return true
    }
    // Check columns
    for (col in 0..2) {
        if (board.all { it[col] == player }) return true
    }
    // Check diagonals
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true
    return false
}

fun getAIMove(
    board: List<List<String?>>,
    aiPlayer: String,
    difficulty: PlayerSelection
): Pair<Int, Int>? {
    val availableMoves = mutableListOf<Pair<Int, Int>>()
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == null) availableMoves.add(Pair(i, j))
        }
    }
    if (availableMoves.isEmpty()) return null

    return when (difficulty) {
        PlayerSelection.Easy -> availableMoves.random()
        PlayerSelection.Medium -> {
            // Simple: prefer center, then corners, then sides
            val preferred = listOf(
                Pair(1, 1),
                Pair(0, 0),
                Pair(0, 2),
                Pair(2, 0),
                Pair(2, 2),
                Pair(0, 1),
                Pair(1, 0),
                Pair(1, 2),
                Pair(2, 1)
            )
            preferred.find { it in availableMoves } ?: availableMoves.random()
        }

        PlayerSelection.Unbeatable -> {
            // Minimax
            minimax(board, aiPlayer, aiPlayer, 0).move
        }

        else -> availableMoves.random()
    }
}

data class MinimaxResult(val score: Int, val move: Pair<Int, Int>?)

fun minimax(
    board: List<List<String?>>,
    player: String,
    aiPlayer: String,
    depth: Int
): MinimaxResult {
    if (checkWin(board, if (player == "X") "O" else "X")) return MinimaxResult(-10 - depth, null)
    if (checkWin(board, player)) return MinimaxResult(10 - depth, null)
    if (board.flatten().none { it == null }) return MinimaxResult(0, null)

    val opponent = if (player == "X") "O" else "X"
    var bestScore = if (player == aiPlayer) Int.MIN_VALUE else Int.MAX_VALUE
    var bestMove: Pair<Int, Int>? = null

    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == null) {
                val newBoard = board.map { it.toMutableList() }.toMutableList()
                newBoard[i][j] = player
                val result = minimax(newBoard, opponent, aiPlayer, depth + 1)
                if (player == aiPlayer) {
                    if (result.score > bestScore) {
                        bestScore = result.score
                        bestMove = Pair(i, j)
                    }
                } else {
                    if (result.score < bestScore) {
                        bestScore = result.score
                        bestMove = Pair(i, j)
                    }
                }
            }
        }
    }
    return MinimaxResult(bestScore, bestMove)
}
