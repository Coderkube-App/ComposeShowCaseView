package com.ckpackage.composeshowcaseview

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Preferences(private val context: Context) {

    fun show(key: String) {
        val preferences: SharedPreferences = context.getSharedPreferences(SHOWCASE_VIEW_JETPACK, Context.MODE_PRIVATE)
        preferences.edit {
            putBoolean(key, true)
        }
    }

    fun getShowing(key: String): Boolean {
        val preferences: SharedPreferences = context.getSharedPreferences(SHOWCASE_VIEW_JETPACK, Context.MODE_PRIVATE)
        return preferences.getBoolean(key, false)
    }

    companion object {
        const val SHOWCASE_VIEW_JETPACK = "SHOWCASE_VIEW_JETPACK"
    }

}