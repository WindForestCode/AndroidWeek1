package com.myschool.app1mvvm.adapter

import androidx.recyclerview.widget.DiffUtil
import com.myschool.app1mvvm.model.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Note, newItem: Note): Any? =
        NotePayload(favorite = newItem.isFavorite.takeIf {
            it != oldItem.isFavorite
        }).takeIf { it.isNotEmpty() }
}