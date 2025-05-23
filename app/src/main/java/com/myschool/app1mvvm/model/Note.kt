package com.myschool.app1mvvm.model

data class Note(
    val id: Long,
    val title: String? = null,
    val text: String,
    val createdAt: String,
    val editedAt: String,
    val isFavorite: Boolean = false,
)
