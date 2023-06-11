package com.example.newsapp.ui.news

import android.app.Application
import android.content.Context
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.newsApi.repository.EverythingNewsRepository
import com.example.newsapp.data.remote.newsApi.response.common.Article
import kotlinx.coroutines.launch

class SearchNewsViewModel(val app: Application) : AndroidViewModel(app) {
    // View表示用変数
    var isLoading = MutableLiveData(false)
    var isError = MutableLiveData(false)
    var isRefresh = MutableLiveData(false)
    var hasFocusOnSearchView = MutableLiveData(true)

    var searchWord: String? = null

    var historyListAdapter: SearchWordHistoryAdapter? = null
    var historyList = mutableListOf<String>()
    var newsListAdapter: NewsListAdapter? = null
    var newsList = mutableListOf<Article>()

    // News APIで取得するデータのページ
    private var page = 1

    /**
     * SearchViewのキーワード入力完了時のリスナー
     */
    fun getOnQueryTextListener(context: Context) = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchWord = query
            fetchNews(context, true)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean = false
    }

    /**
     * キーワードに一致したニュースを取得する
     */
    fun fetchNews(context: Context, isInitial: Boolean) {
        if (searchWord.isNullOrBlank()) return
        if (isInitial) {
            isLoading.value = true
            isError.value = false
            isRefresh.value = false
            hasFocusOnSearchView.value = false

            page = 1
            newsList.clear()
        }

        viewModelScope.launch {
            try {
                val everythingNewsRepository = EverythingNewsRepository(context)
                val result = everythingNewsRepository.getEverythingNews(searchWord!!, page)
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