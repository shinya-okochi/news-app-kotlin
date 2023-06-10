package com.example.newsapp.data.remote.newsApi.repository

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.data.remote.newsApi.dataSource.EverythingNewsDataSource

class EverythingNewsRepository(
    private val context: Context,
) {
    private val everythingNewsDataSource = EverythingNewsDataSource(context)

    suspend fun getEverythingNews(
        q: String,
        page: Int,
    ) = everythingNewsDataSource.getEverythingNews(
        apiKey = context.getString(R.string.news_api_key),
        q = q,
        page = page,
    )
}