package com.github.goutarouh.englishstudy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.goutarouh.englishstudy.R
import com.github.goutarouh.englishstudy.databinding.FragmentShowSentenceDetailBinding
import kotlin.math.abs

class ShowSentenceDetailFragment: Fragment() {

    lateinit var binding: FragmentShowSentenceDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShowSentenceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.detailFragment.setOnTouchListener { _, event ->
            backSHowSentencesFragmentGesture.onTouchEvent(event)
        }
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