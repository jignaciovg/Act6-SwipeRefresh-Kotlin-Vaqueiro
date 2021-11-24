package com.vaqueiro.aplicacionnotas.ui.notedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vaqueiro.aplicacionnotas.R
import com.vaqueiro.aplicacionnotas.core.Resource
import com.vaqueiro.aplicacionnotas.data.local.AppDatabase
import com.vaqueiro.aplicacionnotas.data.local.LocalDataSource
import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.remote.ApiClient
import com.vaqueiro.aplicacionnotas.data.remote.NoteDataSource
import com.vaqueiro.aplicacionnotas.databinding.FragmentNoteEditBinding
import com.vaqueiro.aplicacionnotas.presentation.NoteViewModel
import com.vaqueiro.aplicacionnotas.presentation.NoteViewModelFactory
import com.vaqueiro.aplicacionnotas.repository.NoteRepositoryImp
import com.vaqueiro.aplicacionnotas.ui.notes.adapters.NoteAdapter

class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {

    private lateinit var binding: FragmentNoteEditBinding
    private val viewModel by viewModels<NoteViewModel> {
        NoteViewModelFactory(NoteRepositoryImp(
            LocalDataSource(AppDatabase.getDataBase(this.requireContext()).noteDao()),
            NoteDataSource(ApiClient.service)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteEditBinding.bind(view)

        binding.btnAddNote.setOnClickListener{

            var note = Note(0,binding.editTitle.text.toString(),binding.editContent.text.toString(),binding.editImageUrl.text.toString())

            viewModel.saveNote(note).observe(viewLifecycleOwner, Observer { result ->
                when(result){
                    is Resource.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                        findNavController().popBackStack()
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(),"${result.exception.toString()}",Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }


}