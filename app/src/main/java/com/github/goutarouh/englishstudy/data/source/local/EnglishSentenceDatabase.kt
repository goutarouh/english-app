package com.github.goutarouh.englishstudy.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.goutarouh.englishstudy.data.EnglishSentence

/**
 * The Room Database that contains the EnglishSentence table.
 */
@Database(entities = [EnglishSentence::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class  EnglishSentenceDatabase: RoomDatabase() {
    abstract fun englishSentenceDao(): EnglishSentenceDao
}