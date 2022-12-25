package com.olivierloukombo.android_notes_app.vm

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olivierloukombo.android_notes_app.data.model.Note
import com.olivierloukombo.android_notes_app.data.repository.NoteRepository
import com.olivierloukombo.android_notes_app.utils.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "title"
        )
    )
    private val _noteDescription = mutableStateOf(
        NoteTextFieldState(
            hint = "Description"
        )
    )

    val noteTitle: State<NoteTextFieldState> = _noteTitle
    val noteDescription: State<NoteTextFieldState> = _noteDescription
    val noteColor: State<Int> = _noteColor


    private var currentNoteId = 0



    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    repository.getNoteById(noteId).also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.noteTitle,
                            isHintVisible = false
                        )
                        _noteDescription.value = _noteDescription.value.copy(
                            text = note.noteDescription,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }

        }

    }

    fun saveNote() {
        viewModelScope.launch {
            repository.addNote( Note(
                noteTitle = noteTitle.value.text,
                noteDescription = noteDescription.value.text,
                timestamp = System.currentTimeMillis(),
                color = noteColor.value,
                id = currentNoteId
            ))
        }
    }


    fun onTitleChange(value: String) {
        _noteTitle.value = noteTitle.value.copy(
            text = value
        )
    }

    fun onTitleChangeFocus(focusState: FocusState) {
        _noteTitle.value = noteTitle.value.copy(
            isHintVisible = !focusState.isFocused &&
                    noteTitle.value.text.isBlank()
        )
    }

    fun contentWrite(focusState: FocusState) {
        _noteDescription.value = _noteDescription.value.copy(
            isHintVisible = !focusState.isFocused &&
                    _noteDescription.value.text.isBlank()
        )

    }

    fun onValueChange(value: String) {
        _noteDescription.value = _noteDescription.value.copy(
            text = value
        )
    }

}