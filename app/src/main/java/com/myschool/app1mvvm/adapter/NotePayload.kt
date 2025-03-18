package com.myschool.app1mvvm.adapter

data class NotePayload(
    val favorite: Boolean? = null,
){
    fun isNotEmpty(): Boolean = favorite != null
}
