package com.github.goutarouh.englishstudy.ui.component.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.goutarouh.englishstudy.databinding.SentenceItemBinding

class SentenceListAdapter(
  private val sentences: List<String>
) : RecyclerView.Adapter<SentenceListAdapter.SentenceListViewHolder>() {

    inner class SentenceListViewHolder(val binding: SentenceItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SentenceListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SentenceItemBinding.inflate(inflater)
        return SentenceListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SentenceListViewHolder, position: Int) {
        holder.binding.sentence.text = sentences.getOrNull(position) ?: return
    }

    override fun getItemCount() = sentences.size
}