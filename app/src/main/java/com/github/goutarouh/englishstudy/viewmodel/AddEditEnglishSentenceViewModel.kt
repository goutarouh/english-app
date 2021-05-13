package com.github.goutarouh.englishstudy.viewmodel

import androidx.lifecycle.*
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import com.github.goutarouh.englishstudy.data.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditEnglishSentenceViewModel @Inject constructor(
    private val englishSentenceLocalDataSource: EnglishSentencesDataSource
): ViewModel() {

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

    fun start(sentenceId: String?) {
        sentenceId?.let {
            _itemId.value = sentenceId.toInt()
        }
    }

}