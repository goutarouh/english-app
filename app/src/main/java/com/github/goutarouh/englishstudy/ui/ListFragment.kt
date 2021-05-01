package com.github.goutarouh.englishstudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.goutarouh.englishstudy.databinding.FragmentListBinding
import com.github.goutarouh.englishstudy.ui.component.list.SentenceListAdapter

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            this.adapter = SentenceListAdapter(list)
        }
    }
}

val list = mutableListOf<String>().apply {
    repeat(40) {
        this.add("sentence item $it")
    }
}