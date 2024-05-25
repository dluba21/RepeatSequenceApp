package com.example.sound_game_bootcamp_android.data.model

import com.example.sound_game_bootcamp_android.viewmodel.GamePhase

data class GameState (
        val levelCounter: Int = 0,
        val currentGamePhase: GamePhase = GamePhase.NO_PLAY,
        val buttonsState: List<Boolean> = listOf(false, false, false, false),
        //todo вынести настройки в отдельный viewModel
        val isButtonSoundOnSetting: Boolean = true, //settings
        val currentDelaySliderSetting: Float = 0.5f, //settings
        val currentSoundPackIdSetting: Int = 0, //settings
)