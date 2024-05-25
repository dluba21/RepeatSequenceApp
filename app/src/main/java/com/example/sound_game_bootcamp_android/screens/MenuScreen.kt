package com.example.sound_game_bootcamp_android.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sound_game_bootcamp_android.R
import com.example.sound_game_bootcamp_android.viewmodel.GamePhase
import com.example.sound_game_bootcamp_android.viewmodel.GameScreenRoute
import com.example.sound_game_bootcamp_android.viewmodel.GameViewModel

@Preview
@Composable
fun MenuScreen(gameViewModel: GameViewModel = viewModel(), navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.top_app_padding_with_extra))
            .fillMaxSize()
    ){
        OutlinedButton(
            onClick = {
                gameViewModel.resetGameScreen(GamePhase.START)
                navController.navigate(GameScreenRoute.MAIN_GAME.name)
            },
            modifier = Modifier
                .width(150.dp)
                .padding(4.dp)
        ) {
            Text(text = "New game")
        }
        OutlinedButton(
            onClick = {
                gameViewModel.resetGameScreen(GamePhase.FREE_GAME)
                navController.navigate(GameScreenRoute.MAIN_GAME.name)
            },
            modifier = Modifier
                .width(150.dp)
                .padding(4.dp)
        ) {
            Text(text = "Free play")
        }
        OutlinedButton(onClick = { navController.navigate(GameScreenRoute.SETTINGS.name) }, modifier = Modifier
            .width(150.dp)
            .padding(4.dp)) {
            Text(text = "Settings")
        }
        OutlinedButton(onClick = { navController.navigate(GameScreenRoute.ABOUT.name) }, modifier = Modifier
            .width(150.dp)
            .padding(4.dp)) {
            Text(text = "About")
        }
    }
}