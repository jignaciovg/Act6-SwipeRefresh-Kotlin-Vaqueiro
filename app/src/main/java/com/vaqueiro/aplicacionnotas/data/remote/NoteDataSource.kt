package com.vaqueiro.aplicacionnotas.data.remote

import com.vaqueiro.aplicacionnotas.data.extensions.toNoteList
import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.model.NoteList
import kotlinx.coroutines.tasks.await

class NoteDataSource (private val firebase: FireBaseService){

    suspend fun getNotes():NoteList = firebase.notesRef().get().await().toNoteList()

    suspend fun saveNote(note:Note?):Note?{
        note.let {
            firebase.noteRef().set(note!!).await()
        }
        return note
    }

}