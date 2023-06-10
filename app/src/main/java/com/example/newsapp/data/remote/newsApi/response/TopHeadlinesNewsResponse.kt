package com.example.newsapp.data.remote.newsApi.response

import com.example.newsapp.data.remote.newsApi.response.common.Article

data class TopHeadlinesNewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)