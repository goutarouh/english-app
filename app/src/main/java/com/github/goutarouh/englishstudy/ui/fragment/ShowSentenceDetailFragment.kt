package com.github.goutarouh.englishstudy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.goutarouh.englishstudy.R
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.databinding.FragmentShowSentenceDetailBinding
import com.github.goutarouh.englishstudy.util.TimeCalculator
import com.github.goutarouh.englishstudy.viewmodel.ShowSentenceDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class ShowSentenceDetailFragment: Fragment() {

    lateinit var binding: FragmentShowSentenceDetailBinding

    private val args: ShowSentenceDetailFragmentArgs by navArgs()

    private val viewModel: ShowSentenceDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShowSentenceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.item.observe(viewLifecycleOwner) {
            initScreen(it)
        }

        viewModel.start(args.englishSentenceId)
        setupEditButton()
        setupDeleteButton()
        setupNavigation()
    }

    /**
     * 英文情報をViewに反映させる
     */
    private fun initScreen(englishSentence: EnglishSentence) {
        binding.registeredEnglish.text = englishSentence.englishSentence
        binding.registeredJapanese.text = englishSentence.japaneseSentence
        binding.registeredDescription.text = englishSentence.description
        binding.registeredDate.text = TimeCalculator.dateToString(englishSentence.registeredDate)
    }

    /**
     * 削除ボタン
     */
    private fun setupDeleteButton() {
        binding.deleteSentence.setOnClickListener {
            val action = ShowSentenceDetailFragmentDirections.actionShowSentenceDetailFragmentToConfirmDeleteDialog()
            findNavController().navigate(action)
//            viewModel.item.value?.let {
//                viewModel.delete(it)
//            }
        }
    }

    /**
     * 編集ボタン
     */
    private fun setupEditButton() {
        binding.editSentence.setOnClickListener {
            val action = ShowSentenceDetailFragmentDirections
                .actionShowSentenceDetailFragmentToAddEditEnglishSentenceDialogFragment("${args.englishSentenceId}")
            findNavController().navigate(action)
        }
    }

    /**
     * ナビゲーションをセットする
     */
    private fun setupNavigation() {
        viewModel.sentenceDelete.observe(viewLifecycleOwner) {
            val action = ShowSentenceDetailFragmentDirections.actionShowSentenceDetailFragmentToBottomNavigationList()
            findNavController().navigate(action)
        }
    }

}