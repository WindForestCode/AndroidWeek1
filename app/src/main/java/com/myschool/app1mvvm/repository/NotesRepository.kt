package com.myschool.app1mvvm.repository

import com.myschool.app1mvvm.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    fun toFavorite(id: Long)
    fun saveNote(note: Note)
    fun deleteNote(id: Long)
}