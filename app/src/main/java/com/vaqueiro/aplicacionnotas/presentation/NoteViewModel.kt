package com.vaqueiro.aplicacionnotas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.vaqueiro.aplicacionnotas.core.Resource
import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class NoteViewModel (private val repository: NoteRepository):ViewModel(){

    fun fetchNotes() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repository.getNotes()))
        }catch (exception:Exception){
            emit(Resource.Failure(exception))
        }
    }

    fun saveNote(note:Note?) = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repository.saveNote(note)))
        }catch (exception:Exception){
            emit(Resource.Failure(exception))
        }
    }


}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NoteRepository::class.java).newInstance(repository)
    }
}