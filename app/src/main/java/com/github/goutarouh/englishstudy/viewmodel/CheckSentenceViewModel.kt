package com.github.goutarouh.englishstudy.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckSentenceViewModel @Inject constructor(
    private val englishSentenceLocalDataSource: EnglishSentencesDataSource
): ViewModel() {


    init {
        Log.i("hasegawa", "CheckSentenceViewModel")
    }
}