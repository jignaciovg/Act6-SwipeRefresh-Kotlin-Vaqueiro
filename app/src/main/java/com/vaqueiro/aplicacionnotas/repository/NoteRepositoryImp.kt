package com.vaqueiro.aplicacionnotas.repository

import com.vaqueiro.aplicacionnotas.data.local.LocalDataSource
import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.model.NoteList
import com.vaqueiro.aplicacionnotas.data.model.toNoteEntity
import com.vaqueiro.aplicacionnotas.data.remote.NoteDataSource

class NoteRepositoryImp (

    private val localDataSource: LocalDataSource,
    private val dataSource: NoteDataSource):NoteRepository {

        override suspend fun getNotes():NoteList{
            return dataSource.getNotes()
        }

        override suspend fun saveNote(note:Note?):Note?{
            return dataSource.saveNote(note)
        }

}