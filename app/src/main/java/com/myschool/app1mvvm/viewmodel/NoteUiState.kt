package com.myschool.app1mvvm.viewmodel

import com.myschool.app1mvvm.model.Note

data class NoteUiState(
    val note: List<Note> = emptyList()
)
