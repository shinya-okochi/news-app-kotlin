package com.example.newsapp.data.local.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.newsapp.R

object SharedPreferences {
    private lateinit var prefs: SharedPreferences
    private lateinit var TAG: String

    fun initialize(context: Context) {
        TAG = context.getString(R.string.app_name)
        prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
    }

    /**
     * プリファレンスから指定のString値を取得する
     */
    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    /**
     * キー名に紐づくStrong値を格納する
     */
    fun setString(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(key, value)
            apply()
        }
    }
}
