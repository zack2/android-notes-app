package com.olivierloukombo.android_notes_app.data.repository

import androidx.lifecycle.LiveData
import com.olivierloukombo.android_notes_app.data.NoteDao
import com.olivierloukombo.android_notes_app.data.model.Note
import javax.inject.Inject


class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    var noteList: LiveData<List<Note>> = noteDao.getNotes()

    suspend fun getNoteById(id: Int): Note {
        return noteDao.getNoteById(id)
    }

    suspend fun addNote(oneNote: Note) {
        return noteDao.insertNote(oneNote);
    }

    suspend fun updateNote(oneNote: Note) {
        return noteDao.updateNote(oneNote);
    }

    suspend fun deleteNode(oneToDelete: Note) {
        return noteDao.deleteNote(oneToDelete);
    }

    suspend fun deleteNodes() {
        return noteDao.deleteNotes();
    }
}