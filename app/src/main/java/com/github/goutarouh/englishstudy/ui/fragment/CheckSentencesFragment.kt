package com.github.goutarouh.englishstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.goutarouh.englishstudy.databinding.FragmentCheckSentencesBinding
import com.github.goutarouh.englishstudy.viewmodel.CheckSentenceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckSentencesFragment: Fragment() {

    val viewModel: CheckSentenceViewModel by viewModels()

    private lateinit var binding: FragmentCheckSentencesBinding

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

        binding.checkStart.setOnClickListener {
            Log.i("hasegawa", "check start")
        }
    }
}