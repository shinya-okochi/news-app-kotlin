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
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.ui.MainActivity

class SearchNewsFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = SearchNewsFragment()
    }

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: SearchNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchNewsBinding.inflate(inflater, container, false).apply {
            viewModel = this@SearchNewsFragment.viewModel
            lifecycleOwner = this@SearchNewsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            (activity as? MainActivity)?.let { activity ->
                activity.setSupportActionBar(toolbar)
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                toolbar.setNavigationOnClickListener {
                    activity.supportFragmentManager.popBackStack()
                }
            }

            viewModel?.let { viewModel ->
                searchView.apply {
                    setOnQueryTextListener(viewModel.getOnQueryTextListener(requireContext()))
                }

                viewModel.newsListAdapter = NewsListAdapter(viewModel.newsList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                    adapter = viewModel.newsListAdapter
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
                                viewModel.fetchNews(requireContext(), false)
                            }
                        }
                    })
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}