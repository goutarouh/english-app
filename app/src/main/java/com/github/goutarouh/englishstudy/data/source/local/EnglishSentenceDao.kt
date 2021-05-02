package com.github.goutarouh.englishstudy.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.goutarouh.englishstudy.data.EnglishSentence
import java.util.*

@Dao
interface EnglishSentenceDao {

    @Query("SELECT * FROM english_sentences")
    fun observeEnglishSentences(): LiveData<List<EnglishSentence>>

    @Query("SELECT * FROM english_sentences")
    suspend fun getEnglishSentences(): List<EnglishSentence>

    @Query("SELECT * FROM english_sentences WHERE registered_date BETWEEN :from AND :to")
    fun getEnglishSentencesRegisteredBetweenDates(from: Date, to: Date): List<EnglishSentence>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnglishSentences(englishSentence: EnglishSentence)

}