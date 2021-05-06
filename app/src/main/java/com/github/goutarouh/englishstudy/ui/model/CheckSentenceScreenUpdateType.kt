package com.github.goutarouh.englishstudy.ui.model

import com.github.goutarouh.englishstudy.data.EnglishSentence

/**
 * CheckSentenceFragmentで画面をアップデートするときの
 * flow -> liveData -> viewで使用するタイプ
 */
sealed class CheckSentenceScreenUpdateType {

    // 問題開始までに流す
    data class Start(val startTime: Int): CheckSentenceScreenUpdateType()

    // 問題再生中
    data class Check(val order: Int, val englishSentence: EnglishSentence): CheckSentenceScreenUpdateType()

    // 終了時
    object End: CheckSentenceScreenUpdateType()

}