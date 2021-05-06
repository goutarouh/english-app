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
import com.github.goutarouh.englishstudy.databinding.FragmentCheckSentencesEndBinding
import com.github.goutarouh.englishstudy.viewmodel.CheckSentenceViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 問題後のフラグメント
 */
@AndroidEntryPoint
class CheckSentencesEndFragment: Fragment() {

    lateinit var binding: FragmentCheckSentencesEndBinding

    val viewModel: CheckSentenceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckSentencesEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkEnd.setOnClickListener {
            findNavController().navigate(R.id.action_checkSentencesEndFragment_to_bottom_navigation_check)
        }
    }

}