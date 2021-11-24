package com.vaqueiro.aplicacionnotas.data.remote

import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.model.NoteList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("NotasDB")
    suspend fun getNotes(): NoteList

    @POST("NotasDB")
    suspend fun saveNote(@Body note:Note?):Note?

}