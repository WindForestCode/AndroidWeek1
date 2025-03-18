package com.myschool.app1mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.myschool.app1mvvm.repository.NotesRepository


//class NewNoteViewModel(private val repository: NotesRepository): ViewModel() {
//
//
//    init {
//        Log.d("New Vm", "new VM created")
//    }
//
//    fun saveNote(id: Long, title: String, text: String) {
//        repository.saveNote(id, title, text, )
//    }
//
//    override fun onCleared() {
//        Log.d("New Vm", "new VM destroyed")
//        super.onCleared()
//    }
//
//
//}