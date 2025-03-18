package com.myschool.app1mvvm.repository

import android.util.Log
import com.myschool.app1mvvm.database.NoteDao
import com.myschool.app1mvvm.model.Note
import com.myschool.app1mvvm.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNotesRepository(private val dao: NoteDao): NotesRepository {

    override fun getNotes(): Flow<List<Note>> = dao.getAll()
        .map{
            it.map(NoteEntity::toNote)
        }

    override fun toFavorite(id: Long) {
        Log.d("like test", "like repository note id - $id")
        dao.favoriteById(id)
    }

    override fun saveNote(id: Long, title: String, text: String, createdAt: String, editedAt: String) {
       dao.save(NoteEntity.fromNote(Note(id, title, text, createdAt, editedAt)))
    }

    override fun deleteNote(id: Long) {
        dao.deleteById(id)
    }
}