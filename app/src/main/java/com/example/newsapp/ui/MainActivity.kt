package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.data.local.sharedPreferences.SharedPreferences
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.news.NewsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferencesの初期化
        SharedPreferences.initialize(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, NewsFragment.newInstance()).commit()
    }
}