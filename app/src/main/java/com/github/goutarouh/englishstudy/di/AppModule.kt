package com.github.goutarouh.englishstudy.di

import android.content.Context
import androidx.room.Room
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import com.github.goutarouh.englishstudy.data.source.local.EnglishSentenceDatabase
import com.github.goutarouh.englishstudy.data.source.local.EnglishSentenceLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Singleton
    @Provides
    fun provideEnglishSentenceLocalDataSource(
        database: EnglishSentenceDatabase,
        ioDispatcher: CoroutineDispatcher
    ): EnglishSentencesDataSource {
        return EnglishSentenceLocalDataSource(
            database.englishSentenceDao(),
            ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): EnglishSentenceDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            EnglishSentenceDatabase::class.java,
            "EnglishStudy.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}