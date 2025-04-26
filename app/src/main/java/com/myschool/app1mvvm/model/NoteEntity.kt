package com.myschool.app1mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "createdAt")
    val createdAt: String,
    @ColumnInfo(name = "editedAt")
    val editedAt: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false,
) {
    companion object {
        fun fromNote(note: Note): NoteEntity = with(note) {
            NoteEntity(
                id = id,
                title = title,
                text = text,
                isFavorite = isFavorite,
                createdAt = createdAt,
                editedAt = editedAt,
            )
        }
    }

    fun toNote(): Note = Note(
        id = id,
        title = title,
        text = text,
        isFavorite = isFavorite,
        createdAt = createdAt,
        editedAt = editedAt,
    )
}
