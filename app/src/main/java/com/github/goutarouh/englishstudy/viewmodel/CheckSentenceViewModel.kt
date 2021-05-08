package com.github.goutarouh.englishstudy.viewmodel

import androidx.lifecycle.*
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.Start
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.CheckDisplay
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.AnswerDisplay
import com.github.goutarouh.englishstudy.ui.model.CheckSentenceScreenUpdateType.End
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CheckSentenceViewModel @Inject constructor(
    private val englishSentenceLocalDataSource: EnglishSentencesDataSource
): ViewModel() {

    /**
     * 問題再生のflow
     * View側でobserveする。
     *
     * @param untilAnswer 答え表示までのミリ秒
     * @param untilNext   次の問題までのミリ秒
     */
    fun createCheckFlow(untilAnswer: Long, untilNext: Long) = flow {

        // todo 問題取得の絞り込みを行えるようにする。
        // エラーハンドリング
        val result = englishSentenceLocalDataSource.getEnglishSentences()
        result as Result.Success

        // 開始
        emit(Start)

        // 問題と回答再生
        result.data.forEachIndexed { index, englishSentence ->
            // 問題表示
            emit(CheckDisplay(index, englishSentence.japaneseSentence))
            delay(untilAnswer)
            // 答え表示
            emit(AnswerDisplay(index, englishSentence.englishSentence))
            delay(untilNext)
        }

        // 終了
        emit(End)
    }
}