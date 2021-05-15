package com.github.goutarouh.englishstudy.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.data.Result
import com.github.goutarouh.englishstudy.data.source.EnglishSentencesDataSource
import com.github.goutarouh.englishstudy.data.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    /**
     * 英文を編集したらViewに通知する。
     */
    private val _sentenceEdit = MutableLiveData<Unit>()
    val sentenceEdit: LiveData<Unit> = _sentenceEdit

    /**
     * 英文を追加したらViewに通知する。
     */
    private val _sentenceAdd = MutableLiveData<Unit>()
    val sentenceAdd: LiveData<Unit> = _sentenceAdd

    val item: LiveData<EnglishSentence> = _item

    fun start(sentenceId: String?) {
        sentenceId?.let {
            _itemId.value = sentenceId.toInt()
        }
    }


    /**
     * 英文を保存する、保存後にUIに通知する。
     */
    fun saveSentence(englishSentence: EnglishSentence) = viewModelScope.launch {
        if (_itemId.value == null) {
            englishSentenceLocalDataSource.saveEnglishSentences(englishSentence)
            _sentenceAdd.value = Unit
        } else {
            englishSentenceLocalDataSource.updateEnglishSentence(englishSentence)
            _sentenceEdit.value = Unit
        }
    }
}