package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newsapp.databinding.FragmentSearchNewsBinding

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}