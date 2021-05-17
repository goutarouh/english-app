package com.github.goutarouh.englishstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.goutarouh.englishstudy.R
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
        setHasOptionsMenu(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                // todo search
                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.show_sentences_fragment_menu, menu)
    }

    private fun setupListAdapter() {
        adapter = SentenceListAdapter(createSentenceClickListener())
        val divider = DividerItemDecoration(context, LinearLayoutManager(context).orientation)
        binding.recyclerView.apply {
            this.addItemDecoration(divider)
            this.adapter = this@ShowSentencesFragment.adapter
        }
    }

    private fun setupFab() {
        binding.addEnglishSentence.setOnClickListener {
            val action = ShowSentencesFragmentDirections.actionBottomNavigationListToAddEditEnglishSentenceDialogFragment1(null)
            findNavController().navigate(action)
        }
    }

    private fun createSentenceClickListener(): (sentenceId: Int) -> View.OnClickListener {
        return  { sentenceId ->
            View.OnClickListener {
                val action = ShowSentencesFragmentDirections.actionBottomNavigationListToShowSentenceDetailFragment(sentenceId)
                findNavController().navigate(action)
            }
        }
    }
}