package com.github.goutarouh.englishstudy.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.goutarouh.englishstudy.data.EnglishSentence

@Dao
interface EnglishSentenceDao {

    @Query("SELECT * FROM EnglishSentences")
    suspend fun getEnglishSentences(): List<EnglishSentence>

    @Query("SELECT * FROM EnglishSentences WHERE id = :id")
    suspend fun getEnglishSentenceById(id: String): EnglishSentence?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnglishSentences(englishSentence: EnglishSentence)

}