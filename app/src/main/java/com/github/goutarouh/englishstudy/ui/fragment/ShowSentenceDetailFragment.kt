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

        // 英文を削除する。
        binding.deleteSentence.setOnClickListener {
            // 英文を削除する。
        }

        // 右スワイプで英文一覧に戻る
        binding.detailFragment.setOnTouchListener { _, event ->
            backSHowSentencesFragmentGesture.onTouchEvent(event)
        }
    }

    private fun initScreen(englishSentence: EnglishSentence) {
        binding.registeredEnglish.text = englishSentence.englishSentence
        binding.registeredJapanese.text = englishSentence.japaneseSentence
        binding.registeredDescription.text = englishSentence.description
        binding.registeredDate.text = TimeCalculator.dateToString(englishSentence.registeredDate)
    }


    /**
     * 英文一覧のフラグメントに戻るジェスチャー
     */
    private val backSHowSentencesFragmentGesture = GestureDetectorCompat(context, object: GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (e1 == null || e2 == null) return false

            val diffX = e2.x - e1.x
            val diffY = e2.y - e1.y

            if (abs(diffX) > abs(diffY)) {
                if (diffX > 0 && velocityX > 100) {
                    findNavController().navigate(R.id.action_showSentenceDetailFragment_to_bottom_navigation_list)
                }
            }

            return super.onFling(e1, e2, velocityX, velocityY)
        }
    })
}