package com.example.sound_game_bootcamp_android.data.datasource

import androidx.compose.ui.graphics.Color
import com.example.sound_game_bootcamp_android.R
import com.example.sound_game_bootcamp_android.data.model.ButtonItem

object Datasource {
    fun getButtonItems(): List<ButtonItem> {
        var id = 0
        return listOf(
            ButtonItem(id = id++, soundId = 0, color = Color.Green),
            ButtonItem(id = id++, soundId = 0, color = Color.Yellow),
            ButtonItem(id = id++, soundId = 0, color = Color.Red),
            ButtonItem(id = id++, soundId = 0, color = Color.Blue),
        )
    }

    fun getButtonSoundPack(): List<List<Int>> {
        return listOf(
            listOf(R.raw.bubble, R.raw.bubble, R.raw.bubble, R.raw.bubble),
            listOf(R.raw.bubble, R.raw.bubble, R.raw.bubble, R.raw.bubble),
            listOf(R.raw.bubble, R.raw.bubble, R.raw.bubble, R.raw.bubble),
        )
    }

    //todo переделать в data class
    //todo сделать иконки животных и тд
    fun getSoundSettingColor(): List<Pair<Color, String>> {
        return listOf(
            Color(0XFFF6DBFF) to "Music instruments",
            Color(0XFFA1ECD1) to "Animals",
            Color(0XFFC4E8FF) to "Choir"
        )
    }
}