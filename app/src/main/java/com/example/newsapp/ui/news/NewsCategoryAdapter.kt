package com.example.newsapp.ui.news

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class NewsCategoryAdapter(
    fragment: Fragment,
    private val categoryArray: List<String>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = categoryArray.size

    override fun createFragment(position: Int): Fragment =
        NewsContentFragment.newInstance(categoryArray[position])
}