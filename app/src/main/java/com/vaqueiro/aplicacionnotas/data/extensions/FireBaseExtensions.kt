package com.vaqueiro.aplicacionnotas.data.extensions

import com.google.firebase.firestore.QuerySnapshot
import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.model.NoteList

fun QuerySnapshot.toNoteList():NoteList{
    var lista = mutableListOf<Note>()
    for (document in this){
        lista.add(document.toObject(Note::class.java))
    }
    return NoteList(lista)
}