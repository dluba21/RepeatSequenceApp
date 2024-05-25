package com.example.sound_game_bootcamp_android.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sound_game_bootcamp_android.R
import com.example.sound_game_bootcamp_android.viewmodel.GameViewModel


@Preview
@Composable
fun SettingsScreen(
    gameViewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState = gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.top_app_padding_with_extra)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Sound on/off", style = MaterialTheme.typography.headlineSmall)
        Switch(
            checked = uiState.value.isButtonSoundOnSetting,
            onCheckedChange = { newValue ->
                gameViewModel.updateButtonSoundOnSetting(newValue, context)
            },
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp)) // Добавляем отступ между элементами

        Text(text = "Delay between sounds(ms)", style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
        Slider(
            value = uiState.value.currentDelaySliderSetting,
            onValueChange = { newValue -> gameViewModel.updateButtonDelaySetting(newValue, context) },
            steps = 9,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Pack of button sounds", style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { gameViewModel.onClickSoundPackSetting(context) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(gameViewModel.getCurrentSoundPackSettingData(context).first),
            content = {
                Text(
                    text = gameViewModel.getCurrentSoundPackSettingData(context).second,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            },
            modifier = Modifier.size(width = 150.dp, height = 60.dp)
        )
    }
}