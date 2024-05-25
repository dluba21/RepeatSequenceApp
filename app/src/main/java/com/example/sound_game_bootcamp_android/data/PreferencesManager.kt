package com.example.sound_game_bootcamp_android.data

import android.content.Context

object PreferencesManager {
    private val fileName: String = "Preferences"

    fun saveData(key: MyPreferences, value: String, context: Context) {
        val edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit()
        edit.putString(key.name, value)
        edit.apply()
    }

    fun getData(key: MyPreferences, defaultValue: String, context: Context): String {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE).getString(key.name, defaultValue) ?: defaultValue
    }

    fun saveBooleanData(key: MyPreferences, value: Boolean, context: Context) {
        val edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit()
        when (value) {
            true -> edit.putString(key.name, "true")
            false -> edit.putString(key.name, "false")
        }
        edit.apply()
    }

    fun getBooleanData(key: MyPreferences, context: Context): Boolean? {
        return when(context.getSharedPreferences(fileName, Context.MODE_PRIVATE).getString(key.name, null)) {
            "true" -> true
            "false" -> false
            else -> null
        }
    }
}

enum class MyPreferences {
    BEST_SCORE,
    IS_SOUND_ON,
    SOUND_DELAY,
    SOUND_PACK,
}
