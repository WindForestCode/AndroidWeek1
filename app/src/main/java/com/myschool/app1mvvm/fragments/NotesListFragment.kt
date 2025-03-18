package com.myschool.app1mvvm.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.GridLayoutManager
import com.myschool.app1mvvm.R
import com.myschool.app1mvvm.adapter.NotesAdapter
import com.myschool.app1mvvm.database.AppDb
import com.myschool.app1mvvm.databinding.FragmentNotesListBinding
import com.myschool.app1mvvm.itemdecoration.OffsetDecoration
import com.myschool.app1mvvm.model.Note
import com.myschool.app1mvvm.repository.RoomNotesRepository
import com.myschool.app1mvvm.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NotesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNotesListBinding.inflate(inflater, container, false)
        binding.buttonAddNote.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, NoteFragment())
                .addToBackStack(null)
                .commit()
        }

        val viewModel by activityViewModels<NoteViewModel>{
            viewModelFactory {
                initializer { NoteViewModel( RoomNotesRepository(AppDb.getInstance(requireContext().applicationContext).noteDao)) }
            }
        }

        val adapter = NotesAdapter(
            object : NotesAdapter.NoteListener{
                override fun onFavoriteClicked(note: Note) {
                    Log.d("like test", "$note")
                    viewModel.favorite(note.id)
                }

                override fun onNoteClicked(note: Note) {

                    val noteFragment = NoteFragment().apply {
                        arguments = Bundle().apply {
                            putLong(NoteFragment.ARG_NOTE_ID, note.id)
                            putString(NoteFragment.ARG_NOTE_TITLE, note.title)
                            putString(NoteFragment.ARG_NOTE_TEXT, note.text)
                        }
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, noteFragment)
                        .addToBackStack(null)
                        .commit()
                }

//                override fun onNoteLongClicked(note: Note) {
//                    TODO("Not yet implemented")
//                }
            }
        )
        binding.rcView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rcView.adapter = adapter
        binding.rcView.addItemDecoration(
            OffsetDecoration(resources.getDimensionPixelSize(R.dimen.small_spacing))
        )

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach{adapter.submitList(it.note)}
            .launchIn(viewLifecycleOwner.lifecycleScope)
        return binding.root
    }
}