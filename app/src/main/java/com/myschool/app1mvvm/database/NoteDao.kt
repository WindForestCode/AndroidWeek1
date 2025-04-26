package com.myschool.app1mvvm.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.myschool.app1mvvm.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAll(): Flow<List<NoteEntity>>

    @Upsert
    fun save(note: NoteEntity): Long

    @Query(
        """
            UPDATE notes SET
                isFavorite = CASE WHEN isFavorite THEN 0 ELSE 1 END
            WHERE id = :id;
        """
    )
    fun favoriteById(id: Long)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteById(id: Long)
}