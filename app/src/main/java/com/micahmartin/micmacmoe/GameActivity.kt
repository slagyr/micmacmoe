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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micahmartin.micmacmoe.ui.theme.ElectricCyan
import com.micahmartin.micmacmoe.ui.theme.HotMagenta
import com.micahmartin.micmacmoe.ui.theme.MicMacMoeTheme
import com.micahmartin.micmacmoe.ComposeUI.PlayerSelection
import com.micahmartin.micmacmoe.ui.theme.AccentPurple

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MicMacMoeTheme(dynamicColor = false) {
                GameScreen(this)
            }
        }
    }
}

@Composable
fun GameScreen(activity: ComponentActivity) {

    val composeUi: ComposeUI = Context.game.ui as ComposeUI
    val board = remember { composeUi.boardState }
    var currentPlayer by remember { mutableStateOf("X") }
    var gameStatus by remember { mutableStateOf("Player X's turn") }
    var gameOver by remember { mutableStateOf(false) }

    fun makeMove(cell: Int) {
    }

    LaunchedEffect(Unit) {
//        if (player1 != PlayerSelection.Human) {
//            val aiMove = getAIMove(board, "X", player1)
//            aiMove?.let { makeMove(it.first, it.second) }
//        }
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
            TicTacToeGrid(board) { cell -> makeMove(cell) }
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
fun ColumnSpacer() {
    Spacer(
        modifier = Modifier
            .height(84.dp)
            .width(12.dp)
            .background(AccentPurple)
            .glowEffect(AccentPurple)
    )
}

@Composable
fun RowSpacer() {
    Spacer(
        modifier = Modifier
            .height(12.dp)
            .width(256.dp)
            .background(AccentPurple)
            .glowEffect(AccentPurple)
    )
}

@Composable
fun TicTacToeGrid(board: List<String>, onCellClick: (Int) -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            Row {
                TicTacToeCell(board, onCellClick, 0)
                ColumnSpacer()
                TicTacToeCell(board, onCellClick, 1)
                ColumnSpacer()
                TicTacToeCell(board, onCellClick, 2)
            }
            RowSpacer()
            Row {
                TicTacToeCell(board, onCellClick, 3)
                ColumnSpacer()
                TicTacToeCell(board, onCellClick, 4)
                ColumnSpacer()
                TicTacToeCell(board, onCellClick, 5)
            }
            RowSpacer()
            Row {
                TicTacToeCell(board, onCellClick, 6)
                ColumnSpacer()
                TicTacToeCell(board, onCellClick, 7)
                ColumnSpacer()
                TicTacToeCell(board, onCellClick, 8)
            }
        }

    }
}

@Composable
fun TicTacToeCell(board: List<String>, onClick: (Int) -> Unit, cell: Int) {
    val value = board[cell]
    Box(
        modifier = Modifier
            .size(80.dp)
            .clickable(enabled = value == "", onClick = { onClick(cell) })
            .testTag("cell-$cell"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            color = if (value == "X") HotMagenta else ElectricCyan,
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.testTag("cell-$cell-text")
        )
    }
}