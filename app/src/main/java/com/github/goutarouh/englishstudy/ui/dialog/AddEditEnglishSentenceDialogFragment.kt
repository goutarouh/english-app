package com.github.goutarouh.englishstudy.ui.dialog

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.databinding.FragmentAddEditSentenceBinding
import com.github.goutarouh.englishstudy.viewmodel.AddEditEnglishSentenceViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * 英文追加or編集用のダイアログフラグメント
 */
@AndroidEntryPoint
class AddEditEnglishSentenceDialogFragment: DialogFragment() {

    private val viewModel: AddEditEnglishSentenceViewModel by viewModels()

    private val args: AddEditEnglishSentenceDialogFragmentArgs by navArgs()

    private lateinit var binding: FragmentAddEditSentenceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddEditSentenceBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setCancelButton()
        setSaveButton()
        viewModel.start(args.sentenceId)

        dialog?.let { dialog ->
            dialog.window?.let { window ->
                val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
                val height = (resources.displayMetrics.heightPixels * 0.6).toInt()
                window.setLayout(width, height)
                window.attributes?.apply {
                    gravity = Gravity.CENTER
                }
            }
            isCancelable = false
        }


        viewModel.item.observe(viewLifecycleOwner) {
            binding.editEnglishSentence.setText(it.englishSentence)
            binding.editJapaneseSentence.setText(it.japaneseSentence)
            binding.editDescription.setText(it.description)
        }
    }

    /**
     * キャンセルボタンを設定する。
     *
     * 英文IDのnullチェックによりどこからダイアログを開いたか判断し、戻る。
     */
    private fun setCancelButton() {
        binding.cancelSaveSentence.setOnClickListener {
            val action = if (args.sentenceId == null) {
                AddEditEnglishSentenceDialogFragmentDirections.actionAddEditEnglishSentenceDialogFragmentToBottomNavigationList()
            } else {
                val sentenceId = args.sentenceId!!.toInt()
                AddEditEnglishSentenceDialogFragmentDirections
                    .actionAddEditEnglishSentenceDialogFragmentToShowSentenceDetailFragment(sentenceId)
            }
            findNavController().navigate(action)
        }
    }

    /**
     * 保存ボタンを設定する。
     */
    private fun setSaveButton() {
        binding.doSaveSentence.setOnClickListener {

            val registeredDate = viewModel.item.value?.registeredDate ?: Date()

            val englishSentence = EnglishSentence(
                englishSentence = binding.editEnglishSentence.text.toString(),
                japaneseSentence = binding.editJapaneseSentence.text.toString(),
                description = binding.editDescription.text.toString(),
                registeredDate = registeredDate
            ).also { it.id  = args.sentenceId!!.toInt() }

            viewModel.saveSentence(englishSentence)
        }
    }

    /**
     * 英文を保存したら画面に戻る
     */
    private fun setupNavigation() {

        viewModel.sentenceAdd.observe(viewLifecycleOwner) {
            val action = AddEditEnglishSentenceDialogFragmentDirections.actionAddEditEnglishSentenceDialogFragmentToBottomNavigationList()
            findNavController().navigate(action)
        }

        viewModel.sentenceEdit.observe(viewLifecycleOwner) {
            val action = AddEditEnglishSentenceDialogFragmentDirections.actionAddEditEnglishSentenceDialogFragmentToShowSentenceDetailFragment(args.sentenceId!!.toInt())
            findNavController().navigate(action)
        }
    }
}