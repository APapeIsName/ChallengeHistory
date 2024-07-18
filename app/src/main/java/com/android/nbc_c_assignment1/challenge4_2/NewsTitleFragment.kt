package com.android.nbc_c_assignment1.challenge4_2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.nbc_login.R
import com.android.nbc_login.databinding.FragmentNewsTitleBinding

class NewsTitleFragment : Fragment() {
    private var articleTitle: String? = null

    private val binding by lazy { FragmentNewsTitleBinding.inflate(layoutInflater) }

    private val list = List(10) { NewsItem("제목 $it", "내용 $it") }

    private val adapter = NewsTitleAdapter(list)
    private var listener: DetailDataListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DetailDataListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleTitle = it.getString("articleTitle")
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
        fun newInstance(articleTitle: String) =
            NewsTitleFragment().apply {
                arguments = Bundle().apply {
                    putString("articleTitle", articleTitle)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerTitle.adapter = adapter
        binding.recyclerTitle.layoutManager = LinearLayoutManager(requireContext())
        adapter.itemClick = object : NewsTitleAdapter.ItemClick {
            override fun onClick(article: String) {
                listener?.onReceive(article)
            }
        }
    }
}