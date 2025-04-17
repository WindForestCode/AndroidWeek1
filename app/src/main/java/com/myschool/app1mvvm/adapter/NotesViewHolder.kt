package com.myschool.app1mvvm.adapter

import androidx.recyclerview.widget.RecyclerView
import com.myschool.app1mvvm.R
import com.myschool.app1mvvm.databinding.ItemNoteBinding
import com.myschool.app1mvvm.model.Note

class NotesViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note){
        binding.tvNoteTitle.text = note.title
        binding.tvNotetext.text = note.text
        binding.tvDate.text = note.editedAt
        updateFavorite(note.isFavorite)


    }

    private fun updateFavorite(isFavorite: Boolean){
        if(isFavorite){
            binding.imageFavorite.setImageResource(R.drawable.ic_bookmark_24)
        }
        else{
            binding.imageFavorite.setImageResource(R.drawable.ic_bookmark_border_24)
        }
    }
}