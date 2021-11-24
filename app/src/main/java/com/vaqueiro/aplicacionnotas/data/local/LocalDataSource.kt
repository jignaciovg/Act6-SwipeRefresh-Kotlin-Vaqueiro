package com.vaqueiro.aplicacionnotas.data.local

import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.model.NoteEntity
import com.vaqueiro.aplicacionnotas.data.model.NoteList
import com.vaqueiro.aplicacionnotas.data.model.toNoteList

class LocalDataSource(private val noteDao: NoteDao){

    suspend fun getNotes(): NoteList = noteDao.getNotes().toNoteList()

    suspend fun saveNote(note: NoteEntity) = noteDao.saveNote(note)

}