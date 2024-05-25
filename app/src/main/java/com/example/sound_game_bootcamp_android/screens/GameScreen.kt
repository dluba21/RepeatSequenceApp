package com.example.sound_game_bootcamp_android.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sound_game_bootcamp_android.R
import com.example.sound_game_bootcamp_android.components.GameResultAlert
import com.example.sound_game_bootcamp_android.components.LevelBar
import com.example.sound_game_bootcamp_android.components.SoundButton
import com.example.sound_game_bootcamp_android.components.TopAppGameBar
import com.example.sound_game_bootcamp_android.data.MyPreferences
import com.example.sound_game_bootcamp_android.data.PreferencesManager
import com.example.sound_game_bootcamp_android.data.datasource.Datasource
import com.example.sound_game_bootcamp_android.viewmodel.GamePhase
import com.example.sound_game_bootcamp_android.viewmodel.GameViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Preview
@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
    val gameState by gameViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val gameScreenScope = rememberCoroutineScope()

    LaunchedEffect(gameState.currentGamePhase) {
        if (gameState.currentGamePhase == GamePhase.START) {
            gameScreenScope.launch {
                gameViewModel.showButtonsToClick(context)
            }
        }
    }
    TopAppGameBar(
        navController = navController,
        content = {
            MainGamePart(gameViewModel = gameViewModel)
        }
    )
    BackHandler {
        gameScreenScope.cancel()
        gameViewModel.updateGamePhaseState(GamePhase.NO_PLAY)
        navController.popBackStack()
    }
}

@Composable
fun MainGamePart(gameViewModel: GameViewModel = viewModel()) {
    val gameState by gameViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Surface {
        GameResultAlert(
            title = "You win!",
            icon = { Icon(imageVector = Icons.Default.Star, contentDescription = "Star icon") },
            text = "",
            confirmText = "Next",
            onConfirmation = { gameViewModel.nextLevel() },
            isShowAlert = gameState.currentGamePhase == GamePhase.WIN
        )
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(
                    top = dimensionResource(R.dimen.top_app_padding),
                )
        ) {
            LevelBar(gameState.levelCounter,
                Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(54.dp)
                    .width(128.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                SoundButton(
                    onClick = {
                        coroutineScope.launch {
                            gameViewModel.onClickSoundButton(buttonId = 0, context = context)
                        }
                    },
                    buttonData = Datasource.getButtonItems()[0],
                    isHiglighted = gameState.buttonsState[0],
                    isEnabled = gameViewModel.isButtonEnabled()
                )
                SoundButton(
                    onClick = {
                        coroutineScope.launch {
                            gameViewModel.onClickSoundButton(buttonId = 1, context = context)
                        }
                    },
                    buttonData = Datasource.getButtonItems()[1],
                    isHiglighted = gameState.buttonsState[1],
                    isEnabled = gameViewModel.isButtonEnabled()
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                SoundButton(
                    onClick = {
                        coroutineScope.launch {
                            gameViewModel.onClickSoundButton(buttonId = 2, context = context)
                        }
                    },
                    buttonData = Datasource.getButtonItems()[2],
                    isHiglighted = gameState.buttonsState[2],
                    isEnabled = gameViewModel.isButtonEnabled()
                )
                SoundButton(
                    onClick = {
                        coroutineScope.launch {
                            gameViewModel.onClickSoundButton(buttonId = 3, context = context)
                        }
                    },
                    buttonData = Datasource.getButtonItems()[3],
                    isHiglighted = gameState.buttonsState[3],
                    isEnabled = gameViewModel.isButtonEnabled()
                )
                GameResultAlert(
                    title = "You failed!",
                    icon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "Star icon") },
                    confirmText = "Restart",
                    text = """
                    Current Result: ${gameState.levelCounter}
                    Best result ${PreferencesManager.getData(MyPreferences.BEST_SCORE, defaultValue = "0", LocalContext.current)}
                """.trimIndent(),
                    onConfirmation = { gameViewModel.resetGameScreen(GamePhase.START) },
                    isShowAlert = gameState.currentGamePhase == GamePhase.GAME_RESULT,
                )
            }
        }
    }
}
