package com.github.goutarouh.englishstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.goutarouh.englishstudy.R
import com.github.goutarouh.englishstudy.databinding.FragmentCheckSentencesBinding
import com.github.goutarouh.englishstudy.viewmodel.CheckSentenceViewModel

/**
 * 問題を表示する
 */
class CheckSentencesFragment: Fragment() {

    lateinit var binding: FragmentCheckSentencesBinding

    val viewModel: CheckSentenceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckSentencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkNext.setOnClickListener {
            findNavController().navigate(R.id.action_checkSentencesFragment_to_checkSentencesEndFragment)
        }
    }

}