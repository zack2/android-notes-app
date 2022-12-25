package com.olivierloukombo.android_notes_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.olivierloukombo.android_notes_app.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER BY timestamp DESC")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(updatedNote: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE  FROM note")
    suspend fun deleteNotes()
}
