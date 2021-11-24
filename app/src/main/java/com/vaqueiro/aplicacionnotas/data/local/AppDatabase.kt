package com.vaqueiro.aplicacionnotas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaqueiro.aplicacionnotas.data.model.NoteEntity

@Database(entities = [NoteEntity::class],version = 1)

abstract class AppDatabase:RoomDatabase() {

    abstract fun noteDao():NoteDao

    companion object{
        private var INSTANCE:AppDatabase? = null

        fun getDataBase(context: Context):AppDatabase{
            INSTANCE = INSTANCE?: Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "noteApp"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}