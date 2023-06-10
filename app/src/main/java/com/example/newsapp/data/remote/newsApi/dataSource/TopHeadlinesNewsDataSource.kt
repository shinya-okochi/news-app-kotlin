package com.example.newsapp.data.remote.newsApi.dataSource

import android.content.Context
import com.example.newsapp.data.remote.newsApi.NewsApiService
import com.example.newsapp.data.remote.newsApi.response.TopHeadlinesNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TopHeadlinesNewsDataSource(
    private val context: Context,
) {
    suspend fun getTopHeadlinesNews(
        apiKey: String,
        country: String,
        category: String,
        page: Int,
    ): Response<TopHeadlinesNewsResponse?> =
        withContext(Dispatchers.IO) {
            NewsApiService.create(context).getTopHeadlinesNews(apiKey, country, category, page)
        }
}