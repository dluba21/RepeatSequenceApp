package com.example.sound_game_bootcamp_android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LevelBar(level: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.secondary)) {
        Text(
            text = """
            Score: $level
        """.trimIndent(),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = modifier
                .align(Alignment.Center)
        )
    }
}