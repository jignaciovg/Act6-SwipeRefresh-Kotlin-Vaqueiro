package com.vaqueiro.aplicacionnotas.data.model

fun List<NoteEntity>.toNoteList():NoteList{
    val list = mutableListOf<Note>()
    this.forEach{
        noteEntity ->
        list.add(noteEntity.toNote())
    }
    return NoteList(list)
}

fun Note.toNoteEntity():NoteEntity = NoteEntity(
    this.id,
    this.title,
    this.content,
    this.image
)

fun NoteEntity.toNote():Note = Note(
    this.id,
    this.title,
    this.content,
    this.imageUrl
)