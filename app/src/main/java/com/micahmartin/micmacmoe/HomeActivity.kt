package com.micahmartin.micmacmoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micahmartin.micmacmoe.ui.theme.AccentPurple
import com.micahmartin.micmacmoe.ui.theme.ElectricCyan
import com.micahmartin.micmacmoe.ui.theme.HotMagenta
import com.micahmartin.micmacmoe.ui.theme.MicMacMoeTheme
import kotlin.math.cos
import kotlin.math.sin

enum class PlayerSelection {
    Human, Easy, Medium, Unbeatable
}

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MicMacMoeTheme(dynamicColor = false) {
                HomeScreen(onPlayClicked = { player1, player2 ->
                    android.widget.Toast.makeText(
                        this@HomeActivity,
                        "Starting game: Player1 = $player1, Player2 = $player2",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                })
            }
        }
    }
}

@Composable
fun HomeScreen(
    onPlayClicked: (player1: PlayerSelection, player2: PlayerSelection) -> Unit
) {
    val selectedPlayer1 = remember { mutableStateOf(PlayerSelection.Human) }
    val selectedPlayer2 = remember { mutableStateOf(PlayerSelection.Unbeatable) }

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
        GlowBackground()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            TicTacToeText()
            Spacer(modifier = Modifier.height(24.dp))
            SelectPlayerInput("One", selectedPlayer1) { selectedPlayer1.value = it }
            Spacer(modifier = Modifier.height(48.dp))
            SelectPlayerInput("Two", selectedPlayer2) { selectedPlayer2.value = it }
            Spacer(modifier = Modifier.height(88.dp))
            NeonButton(
                text = "Play",
                onClick = {
                    onPlayClicked(selectedPlayer1.value, selectedPlayer2.value)
                }
            )
        }
    }
}

@Composable
private fun SelectPlayerInput(
    playerName: String,
    selectedPlayer1: MutableState<PlayerSelection>,
    playerOneSelected: (PlayerSelection) -> Unit
) {
    Text(
        text = "Player $playerName:",
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(12.dp))

    PlayerSelector(
        playerName = playerName,
        selectedDifficulty = selectedPlayer1.value,
        onDifficultySelected = playerOneSelected
    )
}

@Composable
private fun TicTacToeText() {
    Text(
        text = "Tic Tac Toe",
        fontSize = 56.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(
                    HotMagenta,
                    ElectricCyan
                )
            )
        ),
        modifier = Modifier
            .padding(bottom = 60.dp)
            .graphicsLayer {
                shadowElevation = 20.dp.toPx()
                ambientShadowColor = ElectricCyan
                spotShadowColor = ElectricCyan
            }
    )
}

@Composable
private fun GlowBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val hexagonPath = Path().apply {
            val centerX = size.width / 2
            val centerY = size.height / 2 - 200.dp.toPx()
            val radius = 600.dp.toPx()
            for (i in 0..6) {
                val angle = Math.PI / 3 * i + Math.PI / 6
                val x = centerX + radius * cos(angle).toFloat()
                val y = centerY + radius * sin(angle).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }

        drawPath(
            path = hexagonPath,
            color = AccentPurple.copy(alpha = 0.05f),
            style = Fill
        )
    }
}

@Composable
fun PlayerSelector(
    playerName: String,
    selectedDifficulty: PlayerSelection,
    onDifficultySelected: (PlayerSelection) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        PlayerSelection.entries.forEach { difficulty ->
            PillButton(
                playerName = playerName,
                text = difficulty.name,
                isSelected = difficulty == selectedDifficulty,
                onClick = { onDifficultySelected(difficulty) }
            )
        }
    }
}

@Composable
fun PillButton(playerName: String, text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) {
        SolidColor(ElectricCyan)
    } else {
        SolidColor(Color.Black.copy(alpha = 0.3f))
    }

    val borderColor = if (isSelected) {
        SolidColor(HotMagenta)
    } else {
        SolidColor(ElectricCyan)
    }
    Box(
        modifier = Modifier
            .height(50.dp)
            .background(
                brush = backgroundColor,
                shape = RoundedCornerShape(25.dp)
            )
            .border(
                width = 2.dp,
                brush = borderColor,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
            .testTag("player_${playerName}_selector_${text}"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.glowEffect(if (isSelected) ElectricCyan else Color.Transparent)
        )
    }
}

@Composable
fun NeonButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(80.dp)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(HotMagenta, ElectricCyan)
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = ElectricCyan,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp,
            modifier = Modifier
                .glowEffect(ElectricCyan)
                .glowEffect(HotMagenta.copy(alpha = 0.6f))
        )
    }
}

fun Modifier.glowEffect(color: Color, radius: Float = 20f): Modifier = this.then(
    Modifier.graphicsLayer {
        shadowElevation = radius.dp.toPx()
        ambientShadowColor = color
        spotShadowColor = color
    }
)