package com.github.goutarouh.englishstudy.data.source.local

import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.Result.Success
import com.github.goutarouh.englishstudy.data.Result.Error
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * Concrete implementation of a data source as a db.
 */
class EnglishSentenceLocalDataSource (
    private val englishSentenceDao: EnglishSentenceDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): EnglishSentencesDataSource {

    override suspend fun getEnglishSentences(): Result<List<EnglishSentence>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(englishSentenceDao.getEnglishSentences())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getEnglishSentenceById(id: String) = withContext(ioDispatcher) {
        try {
            val englishSentence = englishSentenceDao.getEnglishSentenceById(id)
            if (englishSentence != null) {
                return@withContext Success(englishSentence)
            } else {
                return@withContext Error(Exception("englishSentence not found!"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun saveEnglishSentences(englishSentence: EnglishSentence) = withContext(ioDispatcher) {
        englishSentenceDao.insertEnglishSentences(englishSentence)
    }

}