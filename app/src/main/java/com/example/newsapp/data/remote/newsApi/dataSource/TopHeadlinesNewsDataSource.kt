package com.example.newsapp.data.remote.newsApi.dataSource

import android.content.Context
import com.example.newsapp.data.remote.newsApi.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopHeadlinesNewsDataSource(
    private val context: Context,
) {
    suspend fun getTopHeadlinesNews(
        apiKey: String,
        country: String,
        category: String,
        page: Int,
    ) {
        withContext(Dispatchers.IO) {
            NewsApiService.create(context).getTopHeadlinesNews(apiKey, country, category, page)
        }
    }
}