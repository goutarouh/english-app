package com.github.goutarouh.englishstudy.ui.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.goutarouh.englishstudy.R
import com.github.goutarouh.englishstudy.databinding.FragmentAddEditSentenceBinding
import com.github.goutarouh.englishstudy.viewmodel.AddEditEnglishSentenceViewModel
import com.github.goutarouh.englishstudy.viewmodel.ShowSentenceViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        viewModel.start(args.sentenceId)

        viewModel.item.observe(viewLifecycleOwner) {
            binding.editEnglishSentence.setText(it.englishSentence)
            binding.editJapaneseSentence.setText(it.japaneseSentence)
            binding.editDescription.setText(it.description)
        }


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

        binding.doSaveSentence.setOnClickListener {
//            viewModel.saveEnglishSentence(
//                binding.editEnglishSentence.text.toString(),
//                binding.editJapaneseSentence.text.toString(),
//                binding.editDescription.text.toString()
//            )
//            findNavController().navigate(R.id.action_addEditEnglishSentenceDialogFragment_to_bottom_navigation_list)
        }
    }
}