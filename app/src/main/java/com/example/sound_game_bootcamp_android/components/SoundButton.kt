package com.example.sound_game_bootcamp_android.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sound_game_bootcamp_android.data.model.ButtonItem

@Composable
fun SoundButton(onClick: () -> Unit, buttonData: ButtonItem, isHiglighted: Boolean = true, isEnabled: Boolean = true, modifier: Modifier = Modifier) {
    val backgroundColor = if (isHiglighted) Color.LightGray else buttonData.color

    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        shape = RoundedCornerShape(10.dp),
        enabled = isEnabled,
        modifier = modifier
            .padding(16.dp)
            .height(250.dp)
            .width(170.dp)
    ) {
        Text(text = "")
    }
}