package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}