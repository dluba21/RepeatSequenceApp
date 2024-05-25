package com.example.sound_game_bootcamp_android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sound_game_bootcamp_android.R

@Preview
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.top_app_padding_with_extra)),
    ) {
        Text(
            text = "Project",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "This is a simple project to study Compose, navigation, MVVM, etc. The game is about memorizing sequence of buttons and then repeat it."
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Developer",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Suvorov Andrey\nGitHub: github.com/dluba21"
        )
    }
}