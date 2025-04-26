package com.myschool.app1mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myschool.app1mvvm.model.Note
import com.myschool.app1mvvm.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class NoteViewModel(private val repository: NotesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        repository.getNotes()
            .onEach { notes ->
                _uiState.update {
                    it.copy(note = notes)
                }
            }
            .launchIn(viewModelScope)
    }

    fun saveNote(note: Note) {
        repository.saveNote(note)
    }

    fun favorite(id: Long) {
        repository.toFavorite(id)
    }

    fun delete(id: Long) {
        repository.deleteNote(id)
    }
}