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

    override fun saveNote(note: Note) {
       dao.save(NoteEntity.fromNote(note))
    }

    override fun deleteNote(id: Long) {
        dao.deleteById(id)
    }
}