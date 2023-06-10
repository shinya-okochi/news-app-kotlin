package com.example.newsapp.data.remote.newsApi.repository

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.data.remote.newsApi.dataSource.TopHeadlinesNewsDataSource

class TopHeadlinesNewsRepository(
    private val context: Context,
) {
    private val country = "jp"
    private val topHeadlinesNewsDataSource = TopHeadlinesNewsDataSource(context)

    suspend fun getTopHeadlinesNews(
        category: String,
        page: Int,
    ) = topHeadlinesNewsDataSource.getTopHeadlinesNews(
        apiKey = context.getString(R.string.news_api_key),
        country = country,
        category = category,
        page = page,
    )
}