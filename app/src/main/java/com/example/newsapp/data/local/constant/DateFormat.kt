package com.example.newsapp.data.local.constant

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormat {
    private val local: Locale = Locale.JAPAN
    val ISO_8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", local)
    val JAPANESE_DATE_TIME = SimpleDateFormat("yyyy年M月d日(E) HH時mm分", local)
}