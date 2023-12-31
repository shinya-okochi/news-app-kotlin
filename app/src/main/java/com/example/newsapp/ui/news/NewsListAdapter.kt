package com.example.newsapp.ui.news

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.local.constant.DateFormat
import com.example.newsapp.data.remote.newsApi.response.common.Article
import com.example.newsapp.databinding.ItemNewsCellBinding
import java.util.Calendar
import java.util.Date

class NewsListAdapter(
    private val newsList: List<Article>
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    class ViewHolder(var view: ItemNewsCellBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemNewsCellBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.view.apply {
            if (news.urlToImage.isNullOrBlank()) {
                // No Image画像を表示
                image.setImageResource(R.drawable.img_no_image)
            } else {
                // URLから画像を表示する
                Glide.with(root.context).load(news.urlToImage).into(image)
            }

            title.text = news.title

            // ISO 8601(UTC)から"yyyy年M月d日(E) HH時mm分"(JST)に変換して表示
            news.publishedAt?.let {
                val c = Calendar.getInstance()
                c.time = DateFormat.ISO_8601.parse(news.publishedAt) as Date
                date.text = DateFormat.JAPANESE_DATE_TIME.format(c.time)
            }

            root.setOnClickListener {
                val customTabsIntent = CustomTabsIntent.Builder().build()
                customTabsIntent.launchUrl(root.context, Uri.parse(news.url))
            }
        }
    }
}