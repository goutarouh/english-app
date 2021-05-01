package com.github.goutarouh.englishstudy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EnglishSentences")
data class EnglishSentence(

    @PrimaryKey
    val id: String,

    val sentence: String = "",

    @ColumnInfo(name = "registered_date")
    val registeredDate: String = ""
)