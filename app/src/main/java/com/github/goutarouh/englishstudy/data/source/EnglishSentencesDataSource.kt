package com.github.goutarouh.englishstudy.data.source

import androidx.lifecycle.LiveData
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result

/**
 * ローカルとリモート問わず英文取得のためのデータ取得
 */
interface EnglishSentencesDataSource {

    fun observeEnglishSentences(): LiveData<Result<List<EnglishSentence>>>

    suspend fun getEnglishSentences(): Result<List<EnglishSentence>>

    suspend fun getEnglishSentenceById(id: String): Result<EnglishSentence>

    suspend fun saveEnglishSentences(englishSentence: EnglishSentence)

}