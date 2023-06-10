package com.example.newsapp.data.remote.newsApi.response

import com.example.newsapp.data.remote.newsApi.response.common.Article

data class TopHeadlinesNewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>,
)