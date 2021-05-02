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
import java.util.*

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

    /**
     * 日付の絞り込みが正しくできているかどうか
     */
    @Test
    fun saveEnglishSentences_checkDateOfRegisteredSentences() = runBlocking {
        val calendar = Calendar.getInstance()
        repeat(10) {
            localDataSource.saveEnglishSentences(EnglishSentence(registeredDate = calendar.apply {
                set(2021, 4, it+1)
            }.time))
        }

        val from = calendar.apply {
            set(2021, 4, 2)
        }.time
        val to = calendar.apply {
            set(2021, 4, 4)
        }.time

        val result = localDataSource.getEnglishSentencesBetweenRegisteredDates(from, to)
        assertThat(result.succeeded)
        result as Result.Success
        println(result.data)
        assertThat(result.data).hasSize(3)
        return@runBlocking
    }

}