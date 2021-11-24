package com.vaqueiro.aplicacionnotas.repository

import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.model.NoteList

interface NoteRepository {
    suspend fun getNotes():NoteList
    suspend fun saveNote(note:Note?):Note?
}