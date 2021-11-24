package com.vaqueiro.aplicacionnotas.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteEntity")
data class NoteEntity (

    @PrimaryKey
    val id:Int = 0,
    @ColumnInfo(name = "title")
    val title:String="",
    @ColumnInfo(name = "content")
    val content:String="",
    @ColumnInfo(name = "imageUrl")
    val imageUrl:String=""

)