package com.github.goutarouh.englishstudy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "english_sentences")
data class EnglishSentence(

    @ColumnInfo(name = "english_sentence")
    val englishSentence: String = "",

    @ColumnInfo(name = "japaneseSentence")
    val japaneseSentence: String = "",

    val description: String = "",

    @ColumnInfo(name = "registered_date")
    val registeredDate: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}