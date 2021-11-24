package com.vaqueiro.aplicacionnotas.data.remote

import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.model.NoteList

class NoteDataSource (private val apiService: ApiService){

    suspend fun getNotes():NoteList{
        return apiService.getNotes()
    }

    suspend fun saveNote(note:Note?):Note? = apiService.saveNote(note)

}