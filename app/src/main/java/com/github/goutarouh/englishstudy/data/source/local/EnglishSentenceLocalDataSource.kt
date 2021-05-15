package com.github.goutarouh.englishstudy.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.Result.Success
import com.github.goutarouh.englishstudy.data.Result.Error
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

/**
 * Concrete implementation of a data source as a db.
 */
class EnglishSentenceLocalDataSource internal constructor(
    val englishSentenceDao: EnglishSentenceDao,
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): EnglishSentencesDataSource {

    override fun observeEnglishSentences(): LiveData<Result<List<EnglishSentence>>> {
        return englishSentenceDao.observeEnglishSentences().map {
            Success(it)
        }
    }

    override fun observeEnglishSentenceById(sentenceId: Int): LiveData<Result<EnglishSentence>> {
        return englishSentenceDao.observeEnglishSentenceById(sentenceId).map {
            Success(it)
        }
    }

    override suspend fun getEnglishSentences(): Result<List<EnglishSentence>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(englishSentenceDao.getEnglishSentences())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getEnglishSentencesBetweenRegisteredDates(
        from: Date,
        to: Date
    ) = withContext(ioDispatcher) {
        return@withContext try {
            Success(englishSentenceDao.getEnglishSentencesRegisteredBetweenDates(from, to))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun saveEnglishSentences(englishSentence: EnglishSentence) = withContext(ioDispatcher) {
        englishSentenceDao.insertEnglishSentences(englishSentence)
    }

    /**
     * 英文を変種する
     */
    override suspend fun updateEnglishSentence(englishSentence: EnglishSentence) = withContext(ioDispatcher) {
        englishSentenceDao.updateEnglishSentence(englishSentence)
    }

    /**
     * 英文を削除する。
     */
    override suspend fun deleteEnglishSentences(englishSentence: EnglishSentence) {
        englishSentenceDao.deleteEnglishSentences(englishSentence)
    }

}