package com.myschool.app1mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.myschool.app1mvvm.R
import com.myschool.app1mvvm.databinding.ItemNoteBinding
import com.myschool.app1mvvm.model.Note


class NotesAdapter(private val listener: NoteListener): ListAdapter<Note,NotesViewHolder>(NoteDiffCallback()) {

    interface NoteListener {
        fun onFavoriteClicked(note: Note)
        fun onNoteClicked(note: Note)
//        fun onNoteLongClicked(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = NotesViewHolder(binding)

        binding.root.setOnClickListener {
            val note = getItem(viewHolder.adapterPosition)
            listener.onNoteClicked(note)
        }

//     Добавить длинное нажатие

        binding.imageFavorite.setOnClickListener {
            listener.onFavoriteClicked(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
}