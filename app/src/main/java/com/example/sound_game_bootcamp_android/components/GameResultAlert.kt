package com.example.sound_game_bootcamp_android.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun GameResultAlert(
    title: String = "Title",
    icon: @Composable (() -> Unit) = {},
    confirmText: String = "",
    text: String = "text",
    onConfirmation: () -> Unit = {},
    isShowAlert: Boolean = false
) {
    if (isShowAlert) {
        AlertDialog(
            icon = icon,
            title = { Text(text = title) },
            text = { Text(text = text) },
            onDismissRequest = { },
            confirmButton = {
                Row() {
                    TextButton(
                        onClick = onConfirmation
                    ) {
                        Text(confirmText)
                    }
                }
            },
        )
    }
}

