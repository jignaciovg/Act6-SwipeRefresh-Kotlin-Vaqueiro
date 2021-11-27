package com.vaqueiro.aplicacionnotas.data.model
import com.google.firebase.firestore.DocumentId

data class Note(
    @DocumentId
    val id:String = "",
    val title:String = "",
    val content:String = "",
    val image:String = ""
)