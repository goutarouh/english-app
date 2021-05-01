package com.github.goutarouh.englishstudy.data.source.local


import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.succeeded
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.assertj.core.api.Assertions.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.O)
class EnglishSentenceLocalDataSourceTest {

    private lateinit var localDataSource: EnglishSentenceLocalDataSource
    private lateinit var database: EnglishSentenceDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, EnglishSentenceDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        localDataSource = EnglishSentenceLocalDataSource(database.englishSentenceDao())
    }

    @Test
    fun saveEnglishSentence_retrieveEnglishSentence() = runBlocking {
        val englishSentence = EnglishSentence("1", "hello", "20211003")
        localDataSource.saveEnglishSentences(englishSentence)

        val result = localDataSource.getEnglishSentenceById(englishSentence.id)
        assertThat(result.succeeded).isTrue
        result as Result.Success
        assertThat(result.data.sentence).isEqualTo(englishSentence.sentence)
        assertThat(result.data.registeredDate).isEqualTo(englishSentence.registeredDate)
        return@runBlocking
    }

}