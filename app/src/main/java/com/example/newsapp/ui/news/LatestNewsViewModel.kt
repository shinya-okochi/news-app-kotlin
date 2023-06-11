package com.example.newsapp.ui.news

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.newsApi.repository.TopHeadlinesNewsRepository
import com.example.newsapp.data.remote.newsApi.response.common.Article
import kotlinx.coroutines.launch

class LatestNewsViewModel(app: Application) : AndroidViewModel(app) {
    // View表示用変数
    var isLoading = MutableLiveData(false)
    var isError = MutableLiveData(false)
    var isRefresh = MutableLiveData(false)

    var category: String? = null

    var newsListAdapter: NewsListAdapter? = null
    var newsList = mutableListOf<Article>()

    // News APIで取得するデータのページ
    private var page = 1

    /**
     * 最新のニュースを取得する
     */
    fun fetchNews(context: Context, isInitial: Boolean) {
        category ?: return
        if (isInitial) {
            isLoading.value = true
            isError.value = false
            isRefresh.value = false

            page = 1
            newsList.clear()
        }

        viewModelScope.launch {
            try {
                val topHeadlinesNewsResponse = TopHeadlinesNewsRepository(context)
                val result = topHeadlinesNewsResponse.getTopHeadlinesNews(category!!, page)
                if (result.isSuccessful) {
                    val response = result.body() ?: throw Exception()
                    newsList.addAll(response.articles)
                    newsListAdapter?.notifyDataSetChanged()
                    page = page.inc()
                    isLoading.value = false
                } else throw Exception()
            } catch (e: Exception) {
                isError.value = true
                isLoading.value = false
            }
        }
    }
}