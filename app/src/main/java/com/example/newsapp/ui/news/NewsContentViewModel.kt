package com.example.newsapp.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.remote.newsApi.response.common.Article

class NewsContentViewModel(app: Application) : AndroidViewModel(app) {
    // View表示用変数
    var isLoading = MutableLiveData(false)
    var isError = MutableLiveData(false)

    var newsContentAdapter : NewsContentAdapter? = null
    var newsList = mutableListOf<Article>()
}