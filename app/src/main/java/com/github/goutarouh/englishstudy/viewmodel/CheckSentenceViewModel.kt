package com.github.goutarouh.englishstudy.viewmodel

import androidx.lifecycle.*
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.Start
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.Check
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.End
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CheckSentenceViewModel @Inject constructor(
    private val englishSentenceLocalDataSource: EnglishSentencesDataSource
): ViewModel() {

    val checkFlow = flow {
        val result = englishSentenceLocalDataSource.getEnglishSentences()
        result as Result.Success
        repeat(3) {
            emit(Start(3 - it))
            delay(1000)
        }
        result.data.forEachIndexed { index, englishSentence ->
            emit(Check(index, englishSentence))
            delay(2000)
        }
        emit(End)
    }
}