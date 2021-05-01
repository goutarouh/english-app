package com.github.goutarouh.englishstudy.viewmodel

import androidx.lifecycle.*
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import com.github.goutarouh.englishstudy.data.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowSentenceViewModel @Inject constructor(
    private val englishSentenceLocalDataSource: EnglishSentencesDataSource
): ViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    private val _items: LiveData<List<EnglishSentence>> = _forceUpdate.switchMap { forceUpdate ->
        val result = MutableLiveData<List<EnglishSentence>>()

        englishSentenceLocalDataSource.observeEnglishSentences().distinctUntilChanged().switchMap {
            if (it.succeeded) {
                result.value = (it as Result.Success).data
            } else {
                result.value = emptyList()
            }
            result
        }
    }

    val items: LiveData<List<EnglishSentence>> = _items


    init {
        loadEnglishSentences(true)
    }

    private fun loadEnglishSentences(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

}