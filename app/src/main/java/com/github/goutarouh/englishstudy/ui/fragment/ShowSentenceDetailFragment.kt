package com.github.goutarouh.englishstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.goutarouh.englishstudy.databinding.FragmentShowSentenceDetailBinding

class ShowSentenceDetailFragment: Fragment() {

    lateinit var binding: FragmentShowSentenceDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShowSentenceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}