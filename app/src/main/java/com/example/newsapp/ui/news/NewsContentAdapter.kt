package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.remote.newsApi.response.common.Article
import com.example.newsapp.databinding.ItemNewsContentBinding

class NewsContentAdapter(
    private val newsList: List<Article>
) : RecyclerView.Adapter<NewsContentAdapter.ViewHolder>() {
    class ViewHolder(var view : ItemNewsContentBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemNewsContentBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}