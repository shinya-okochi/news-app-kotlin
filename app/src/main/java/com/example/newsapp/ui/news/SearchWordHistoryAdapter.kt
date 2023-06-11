package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemSearchHistoryCellBinding

class SearchWordHistoryAdapter(
    private val historyList: List<String>,
    private val callback: SearchWordHistoryCallback,
) : RecyclerView.Adapter<SearchWordHistoryAdapter.ViewHolder>() {
    class ViewHolder(val view: ItemSearchHistoryCellBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemSearchHistoryCellBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = historyList[position]
        holder.view.apply {
            searchWord.text = word

            closeBtn.setOnClickListener { callback.onClickDeleteBtn(word) }

            root.setOnClickListener { callback.onClick(word) }
        }
    }

    interface SearchWordHistoryCallback {
        fun onClick(word: String)
        fun onClickDeleteBtn(word: String)
    }
}