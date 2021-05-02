package com.github.goutarouh.englishstudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.goutarouh.englishstudy.databinding.FragmentShowSentencesBinding
import com.github.goutarouh.englishstudy.ui.component.adapter.SentenceListAdapter
import com.github.goutarouh.englishstudy.viewmodel.ShowSentenceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowSentencesFragment: Fragment() {

    val viewModel: ShowSentenceViewModel by viewModels()

    lateinit var adapter: SentenceListAdapter

    private lateinit var binding: FragmentShowSentencesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowSentencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        setupListAdapter()
        setupFab()
    }

    private fun setupListAdapter() {
        adapter = SentenceListAdapter()
        binding.recyclerView.apply {
            this.adapter = this@ShowSentencesFragment.adapter
        }
    }

    private fun setupFab() {
        binding.addEnglishSentence.setOnClickListener {
            viewModel.addEnglishSentence()
        }
    }
}