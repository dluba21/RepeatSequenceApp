package com.example.sound_game_bootcamp_android.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.ui.graphics.Color

import androidx.lifecycle.ViewModel
import com.example.sound_game_bootcamp_android.R
import com.example.sound_game_bootcamp_android.data.MyPreferences
import com.example.sound_game_bootcamp_android.data.PreferencesManager
import com.example.sound_game_bootcamp_android.data.datasource.Datasource
import com.example.sound_game_bootcamp_android.data.model.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameState())
    val uiState = _uiState.asStateFlow()

    private val buttonsAtStartConfig = 4
    private val biggestSoundDelay = 1000L
    private val defaultDelayTime = 500L
    private val buttonsToPressByLevel = _uiState.value.levelCounter + buttonsAtStartConfig

    private val expectedButtons = mutableListOf<Int>()
    private var currentButtonCounter = 0



    init {
        Log.i("Test", "ViewModel created!")
        setRandomButtonListByLevel()
    }

    /**
     * play music with button
     */
    suspend fun playButton(soundId: Int = R.raw.bubble, context: Context, delayDuration: Long) {
        val player = MediaPlayer.create(context, soundId)

        player.start()
        delay(delayDuration)
        player.stop()
    }

    private fun setRandomButtonListByLevel() {
        val buttonsNumber = Datasource.getButtonItems().size
        repeat(buttonsToPressByLevel) {
            expectedButtons.add((0 until buttonsNumber).shuffled().first())
        }
//        expectedButtons.addAll(0 until 4)
    }

    suspend fun onClickSoundButton(buttonId: Int, context: Context) {
        playButton(getSoundByButtonId(buttonId, context), context, defaultDelayTime)
        if (_uiState.value.currentGamePhase == GamePhase.FREE_GAME) {
            return
        }
        if (buttonId == expectedButtons[currentButtonCounter] && currentButtonCounter == buttonsToPressByLevel - 1) {
            _uiState.update { state ->
                state.copy(
                    levelCounter = state.levelCounter.inc(),
                    currentGamePhase = GamePhase.WIN,
                )
            }
        } else if (buttonId == expectedButtons[currentButtonCounter]) {
            currentButtonCounter++
        } else {
            val previousBestResult = PreferencesManager.getData(key = MyPreferences.BEST_SCORE, context = context, defaultValue = "0")
            PreferencesManager.saveData(
                key = MyPreferences.BEST_SCORE,
                value = (Math.max(_uiState.value.levelCounter, previousBestResult.toIntOrNull() ?: 0)).toString(),
                context = context
            )
            _uiState.update { state ->
                state.copy(
                    currentGamePhase = GamePhase.GAME_RESULT
                )
            }
        }
    }
    fun resetGameScreen(gamePhase: GamePhase) {
        resetLevelVars()
        _uiState.update { state ->
            state.copy(
                levelCounter = 0,
                currentGamePhase = gamePhase,
                buttonsState = getDefaultButtonStateList()
            )
        }
    }

    fun updateGamePhaseState(gamePhase: GamePhase) {
        _uiState.update { state -> state.copy(currentGamePhase = gamePhase) }
    }

    private fun resetLevelVars() {
        currentButtonCounter = 0
        setRandomButtonListByLevel()
    }

    //toask лучше корутину вызывать из composable или из метода viewmodel?
    suspend fun showButtonsToClick(context: Context) {
        delay(defaultDelayTime)
        for (currentButtonShowCounter in 0 until buttonsToPressByLevel) {
            val newList = getDefaultButtonStateList().toMutableList()
            newList[expectedButtons[currentButtonShowCounter]] = true
            if (uiState.value.currentGamePhase != GamePhase.START) break //если вне корутины состояние обновится, то корутина остановится

            _uiState.update { state -> state.copy(buttonsState = newList) }
            playButton(
                soundId = getSoundByButtonId(currentButtonShowCounter, context),
                delayDuration = getCurrentDelayTime(context),
                context = context
            )
            _uiState.update { state -> state.copy(buttonsState = getDefaultButtonStateList()) }
        }
        _uiState.update { state ->
            state.copy(
                currentGamePhase = GamePhase.IN_ACTION,
                buttonsState = getDefaultButtonStateList()
            )
        }
    }

    private fun getSoundByButtonId(buttonId: Int, context: Context): Int {
        val soundPackId = PreferencesManager.getData(MyPreferences.SOUND_PACK, "0", context).toIntOrNull() ?: 0
        return Datasource.getButtonSoundPack()[soundPackId][buttonId]
    }

    fun onClickSoundPackSetting(context: Context) {
        val soundPackId = PreferencesManager.getData(MyPreferences.SOUND_PACK, "0", context).toIntOrNull() ?: 0
        val newValue = if (soundPackId + 1 < 3) (soundPackId + 1) else 0
        PreferencesManager.saveData(MyPreferences.SOUND_PACK, newValue.toString(), context)

        _uiState.update { state -> state.copy(currentSoundPackIdSetting = newValue) }
    }

    fun getCurrentSoundPackSettingData(context: Context): Pair<Color, String> {
        val soundPackId = PreferencesManager.getData(MyPreferences.SOUND_PACK, "0", context).toIntOrNull() ?: 0
        return Datasource.getSoundSettingColor()[soundPackId]
    }

    fun nextLevel() {
        resetLevelVars()
        _uiState.update {
            state -> state.copy(currentGamePhase = GamePhase.START)
        }
    }

    private fun getCurrentDelayTime(context: Context): Long {
        return ((PreferencesManager.getData(MyPreferences.SOUND_DELAY, "0.5", context).toFloatOrNull() ?: 0.5f) * biggestSoundDelay).toLong()
    }

    private fun getDefaultButtonStateList(): List<Boolean> {
        return listOf(false, false, false, false)
    }

    fun isButtonEnabled(): Boolean {
        return _uiState.value.currentGamePhase == GamePhase.IN_ACTION ||
                _uiState.value.currentGamePhase == GamePhase.FREE_GAME
    }

    fun updateButtonSoundOnSetting(newValue: Boolean, context: Context) {
        _uiState.update { state -> state.copy(isButtonSoundOnSetting = newValue) }
        PreferencesManager.saveBooleanData(MyPreferences.IS_SOUND_ON, newValue, context)
    }

    fun updateButtonDelaySetting(newValue: Float, context: Context) {
        _uiState.update { state -> state.copy(currentDelaySliderSetting = newValue) }
        PreferencesManager.saveData(MyPreferences.SOUND_DELAY, newValue.toString(), context)
    }
}
