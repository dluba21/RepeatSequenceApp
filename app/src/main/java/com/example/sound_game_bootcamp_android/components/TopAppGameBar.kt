package com.example.sound_game_bootcamp_android.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sound_game_bootcamp_android.viewmodel.GamePhase
import com.example.sound_game_bootcamp_android.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppGameBar(gameViewModel: GameViewModel = viewModel(), content: @Composable () -> Unit = {}, navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Game App", textAlign = TextAlign.Center) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = {
                            navController.navigateUp()
                            gameViewModel.updateGamePhaseState(GamePhase.NO_PLAY)
                        }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Move back"
                            )
                        }
                    }

                }
            )
        },
        content = {
            it.calculateTopPadding()
            content()
        }
    )
}