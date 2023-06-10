package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsContentBinding

class NewsContentFragment : Fragment() {
    companion object {
        private const val ARG_CATEGORY_NAME = "ARG_CATEGORY_NAME"

        @JvmStatic
        fun newInstance(categoryName: String) = NewsContentFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CATEGORY_NAME, categoryName)
            }
        }
    }

    private var _binding: FragmentNewsContentBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: NewsContentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsContentBinding.inflate(inflater, container, false).apply {
            viewModel = this@NewsContentFragment.viewModel
            lifecycleOwner = this@NewsContentFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // 色の設定
            swipeRefresh.setColorSchemeColors(requireContext().getColor(R.color.light_gray))

            viewModel?.let {
                it.category = arguments?.getString(ARG_CATEGORY_NAME)!!

                it.newsContentAdapter = NewsContentAdapter(it.newsList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                    adapter = it.newsContentAdapter
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(), LinearLayoutManager(requireContext()).orientation
                        )
                    )
                    addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            // リストの末尾に来た時の処理
                            if (recyclerView.canScrollVertically(1).not()) {
                                it.fetchNews(requireContext(), false)
                            }
                        }
                    })
                }
            }
        }

        viewModel.fetchNews(requireContext(), true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}