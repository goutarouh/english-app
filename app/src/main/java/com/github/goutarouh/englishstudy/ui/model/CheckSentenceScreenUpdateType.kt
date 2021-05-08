package com.github.goutarouh.englishstudy.ui.model


/**
 * CheckSentenceFragmentで画面をアップデートするときの
 * flow -> liveData -> viewで使用するタイプ
 */
sealed class CheckSentenceScreenUpdateType {

    // 問題開始までに流す
    object Start: CheckSentenceScreenUpdateType()

    // 問題表示
    data class CheckDisplay(val order: Int, val japaneseSentence: String)
        : CheckSentenceScreenUpdateType()

    // 答え表示
    data class AnswerDisplay(val order: Int, val englishSentence: String)
        : CheckSentenceScreenUpdateType()

    // 終了時
    object End: CheckSentenceScreenUpdateType()

}