package com.olivierloukombo.android_notes_app.utils

import com.olivierloukombo.android_notes_app.data.model.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
)