package com.example.newsapp.data.local

import com.example.newsapp.data.local.sharedPreferences.SharedPreferences
import org.json.JSONArray

object DataManager {
    private const val KEY_SEARCH_WORD_HISTORY = "key_search_word_history"

    /**
     * 検索履歴
     */
    var searchWordHistoryList: MutableList<String>
        get() {
            val string =
                SharedPreferences.getString(KEY_SEARCH_WORD_HISTORY) ?: return mutableListOf()
            val json = JSONArray(string)
            val result = mutableListOf<String>()
            for (i in 0 until json.length()) {
                result.add(json.get(i) as String)
            }
            return result
        }
        set(value) {
            val json = JSONArray(value.distinct())
            SharedPreferences.setString(KEY_SEARCH_WORD_HISTORY, json.toString())
        }
}