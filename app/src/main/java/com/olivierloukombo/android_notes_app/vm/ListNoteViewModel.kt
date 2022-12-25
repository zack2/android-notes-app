package com.olivierloukombo.android_notes_app.vm

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olivierloukombo.android_notes_app.data.model.Note
import com.olivierloukombo.android_notes_app.data.repository.NoteRepository
import com.olivierloukombo.android_notes_app.utils.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListNoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    init {
        getNotes()
    }

    private fun getNotes() {
        repository.noteList.observeForever {

            _state.value = state.value.copy(
                notes = it
            )
        }
    }
}
