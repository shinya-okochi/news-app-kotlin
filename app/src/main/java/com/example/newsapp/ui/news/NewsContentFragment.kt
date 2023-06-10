package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
            viewModel?.let {
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
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchNews(requireContext(), arguments?.getString(ARG_CATEGORY_NAME)!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}