package com.example.newsapp.data.remote.newsApi.dataSource

import android.content.Context
import com.example.newsapp.data.remote.newsApi.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EverythingNewsDataSource(
    private val context: Context,
) {
    suspend fun getEverythingNews(
        apiKey: String,
        q: String,
        page: Int,
    ) {
        withContext(Dispatchers.IO) {
            NewsApiService.create(context).getEverythingNews(apiKey, q, page)
        }
    }
}