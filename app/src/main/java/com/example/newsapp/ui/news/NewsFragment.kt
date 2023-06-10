package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.data.local.constant.NewsCategory
import com.example.newsapp.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayoutMediator

class NewsFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    private var _binding: FragmentNewsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // タブに表示するカテゴリ
            val categoryArray = NewsCategory.values().map { it.categoryName }
            viewPager.adapter = NewsCategoryAdapter(this@NewsFragment, categoryArray)
            // TabLayout を ViewPager2 にリンクしアタッチ
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = categoryArray[position]
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}