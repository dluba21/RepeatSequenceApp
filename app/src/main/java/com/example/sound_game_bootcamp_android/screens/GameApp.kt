package com.example.sound_game_bootcamp_android.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sound_game_bootcamp_android.components.TopAppGameBar
import com.example.sound_game_bootcamp_android.viewmodel.GameScreenRoute
import com.example.sound_game_bootcamp_android.viewmodel.GameViewModel

@Preview(showBackground = true)
@Composable
fun GameApp(
    gameViewModel: GameViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GameScreenRoute.MENU.name
    ) {
        composable(route = GameScreenRoute.MENU.name) {
            TopAppGameBar(
                navController = navController,
                content =  {
                    MenuScreen(gameViewModel, navController)
                }
            )
        }
        composable(route = GameScreenRoute.MAIN_GAME.name) {
            GameScreen(gameViewModel, navController)
        }
        composable(route = GameScreenRoute.FREE_PLAY.name) {
            GameScreen(gameViewModel, navController)
        }
        composable(route = GameScreenRoute.SETTINGS.name) {
            TopAppGameBar(
                navController = navController,
                gameViewModel = gameViewModel,
                content = {
                    SettingsScreen(gameViewModel, navController)
                }
            )
        }
        composable(route = GameScreenRoute.ABOUT.name) {
            TopAppGameBar(
                navController = navController,
                content =  {
                    AboutScreen()
                }
            )
        }

    }
}