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
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.Check
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
            viewModel.checkFlow.collect {
                when (it) {
                    is Start -> {
                        Log.i("hasegawa", "start")
                        binding.checkJapaneseSentence.text = "${it.startTime}"
                    }
                    is Check -> {
                        Log.i("hasegawa", "check")
                        binding.checkJapaneseSentence.text = it.englishSentence.japaneseSentence
                    }
                    is End -> {
                        // 問題終了のフラグメントへ
                        Log.i("hasegawa", "end")
                        findNavController().navigate(R.id.action_checkSentencesFragment_to_checkSentencesEndFragment)
                    }
                }
            }
        }
    }

}