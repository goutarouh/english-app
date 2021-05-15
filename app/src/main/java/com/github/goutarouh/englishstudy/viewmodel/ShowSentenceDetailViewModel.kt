package com.github.goutarouh.englishstudy.viewmodel

import androidx.lifecycle.*
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import com.github.goutarouh.englishstudy.data.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowSentenceDetailViewModel @Inject constructor (
        private val englishSentenceLocalDataSource: EnglishSentencesDataSource
) : ViewModel() {

    private val _itemId: MutableLiveData<Int> = MutableLiveData()

    private val _item: LiveData<EnglishSentence> = _itemId.switchMap { itemId ->

        val result = MutableLiveData<EnglishSentence>()

        englishSentenceLocalDataSource.observeEnglishSentenceById(itemId).distinctUntilChanged().switchMap {
            if (it.succeeded) {
                result.value = (it as Result.Success).data
            } else {
                throw (it as Result.Error).exception
            }
            result
        }
    }

    val item: LiveData<EnglishSentence> = _item

    private val _sentenceDelete = MutableLiveData<Unit>()
    val sentenceDelete: LiveData<Unit> = _sentenceDelete

    fun start(sentenceId: Int) {
        _itemId.value = sentenceId
    }

    /**
     * 英文を削除する
     */
    fun delete(englishSentence: EnglishSentence) = viewModelScope.launch {
        englishSentenceLocalDataSource.deleteEnglishSentences(englishSentence)
        _sentenceDelete.value = Unit
    }

}