package com.github.goutarouh.englishstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.goutarouh.englishstudy.R
import com.github.goutarouh.englishstudy.databinding.FragmentCheckSentencesBinding
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.Start
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.CheckDisplay
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.AnswerDisplay
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.End
import com.github.goutarouh.englishstudy.viewmodel.CheckSentenceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * 問題を表示する
 */
@AndroidEntryPoint
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

        lifecycleScope.launchWhenResumed {
            viewModel.createCheckFlow(4000, 1000).collect {
                when (it) {
                    is Start -> {
                    }
                    is CheckDisplay -> {
                        binding.answerEnglishSentence.visibility = View.INVISIBLE
                        binding.checkJapaneseSentence.text = it.japaneseSentence
                    }
                    is AnswerDisplay -> {
                        binding.answerEnglishSentence.visibility = View.VISIBLE
                        binding.answerEnglishSentence.text = it.englishSentence
                    }
                    is End -> {
                        // 問題終了のフラグメントへ
                        findNavController().navigate(R.id.action_checkSentencesFragment_to_checkSentencesEndFragment)
                    }
                }
            }
        }
    }

}