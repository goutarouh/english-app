package com.github.goutarouh.englishstudy.ui.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.goutarouh.englishstudy.data.EnglishSentence
import com.github.goutarouh.englishstudy.ui.component.adapter.SentenceListAdapter.ViewHolder
import com.github.goutarouh.englishstudy.databinding.SentenceItemBinding

class SentenceListAdapter(): ListAdapter<EnglishSentence, ViewHolder>(EnglishSentenceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(val binding: SentenceItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EnglishSentence) {
            binding.sentence.text = item.englishSentence
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInt = LayoutInflater.from(parent.context)
                val binding = SentenceItemBinding.inflate(layoutInt, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class EnglishSentenceDiffCallback: DiffUtil.ItemCallback<EnglishSentence>() {
    override fun areItemsTheSame(oldItem: EnglishSentence, newItem: EnglishSentence): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EnglishSentence, newItem: EnglishSentence): Boolean {
        return oldItem == newItem
    }

}