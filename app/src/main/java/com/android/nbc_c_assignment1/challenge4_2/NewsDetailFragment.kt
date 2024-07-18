package com.android.nbc_c_assignment1.challenge4_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.nbc_login.R
import com.android.nbc_login.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {
    private var articleDetail: String? = null

    private val binding by lazy { FragmentNewsDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleDetail = it.getString("articleDetail")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(articleDetail: String) =
            NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("articleDetail", articleDetail)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detail.text = articleDetail
    }
}