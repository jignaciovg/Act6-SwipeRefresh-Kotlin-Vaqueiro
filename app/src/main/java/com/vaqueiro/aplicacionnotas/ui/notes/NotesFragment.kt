package com.vaqueiro.aplicacionnotas.ui.notes

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
import androidx.recyclerview.widget.GridLayoutManager
import com.vaqueiro.aplicacionnotas.R
import com.vaqueiro.aplicacionnotas.core.Resource
import com.vaqueiro.aplicacionnotas.data.local.AppDatabase
import com.vaqueiro.aplicacionnotas.data.local.LocalDataSource
import com.vaqueiro.aplicacionnotas.data.model.Note
import com.vaqueiro.aplicacionnotas.data.remote.ApiClient
import com.vaqueiro.aplicacionnotas.data.remote.FireBaseService
import com.vaqueiro.aplicacionnotas.data.remote.NoteDataSource
import com.vaqueiro.aplicacionnotas.databinding.FragmentNotesBinding
import com.vaqueiro.aplicacionnotas.presentation.NoteViewModel
import com.vaqueiro.aplicacionnotas.presentation.NoteViewModelFactory
import com.vaqueiro.aplicacionnotas.repository.NoteRepositoryImp
import com.vaqueiro.aplicacionnotas.ui.notes.adapters.NoteAdapter


class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NoteAdapter

    private val viewModel by viewModels<NoteViewModel> {
        NoteViewModelFactory(
            NoteRepositoryImp(
                LocalDataSource(AppDatabase.getDataBase(this.requireContext()).noteDao()),
                NoteDataSource(FireBaseService()))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)
        binding.recyclerNotes.layoutManager = GridLayoutManager(this.requireContext(), 2)
        //SWIPE REFRESH
        binding.swipeContainer.setOnRefreshListener {
            viewModel.fetchNotes().observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.recyclerNotes.visibility = View.GONE
                        binding.progressbar.visibility = View.GONE
                        binding.swipeContainer.isRefreshing = true
                        Toast.makeText(this.context, "CARGANDO ...", Toast.LENGTH_LONG).show();
                    }
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                        Log.d("LiveData", "${result.data.toString()}")
                        adapter = NoteAdapter(result.data.data) { note ->
                            onNoteClick(note)
                        }
                        binding.recyclerNotes.adapter = adapter
                        binding.recyclerNotes.visibility = View.VISIBLE
                        binding.swipeContainer.isRefreshing = false
                        Toast.makeText(this.context, "DATOS ACTUALIZADOS", Toast.LENGTH_LONG).show();
                    }
                    is Resource.Failure -> {
                        binding.progressbar.visibility = View.GONE
                        binding.swipeContainer.isRefreshing = false
                        Log.d("LiveData", "${result.exception.toString()}")
                        Toast.makeText(this.context, "NO SE ENCONTRARON RESULTADOS", Toast.LENGTH_LONG).show();
                    }
                }
            })
        }

        binding.btnAddNote.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToNoteEditFragment()
            findNavController().navigate(action)
        }

        viewModel.fetchNotes().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    Log.d("LiveData", "${result.data.toString()}")
                    adapter = NoteAdapter(result.data.data) { note ->
                        onNoteClick(note)
                    }
                    binding.recyclerNotes.adapter = adapter
                }
                is Resource.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    Log.d("LiveData", "${result.exception.toString()}")
                }
            }
        })
    }

    private fun onNoteClick(note: Note) {

        val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(
            note.id,
            note.title,
            note.content,
            note.image
        )

        findNavController().navigate(action)
    }

}

//JOSE IGNACIO VAQUEIRO GLEZ