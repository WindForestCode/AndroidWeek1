package com.myschool.app1mvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.myschool.app1mvvm.R
import com.myschool.app1mvvm.database.AppDb
import com.myschool.app1mvvm.databinding.FragmentNoteBinding
import com.myschool.app1mvvm.repository.RoomNotesRepository
import com.myschool.app1mvvm.viewmodel.NoteViewModel

class NoteFragment : Fragment() {
    companion object{
        const val ARG_NOTE_ID = "ARG_NOTE_ID"
        const val ARG_NOTE_TITLE = "ARG_NOTE_TITLE"
        const val ARG_NOTE_TEXT = "ARG_NOTE_TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoteBinding.inflate(inflater, container, false)

        val viewModel by activityViewModels<NoteViewModel> {
            viewModelFactory { initializer { NoteViewModel(RoomNotesRepository(AppDb.getInstance(requireContext().applicationContext).noteDao)) } }
        }

        val id = arguments?.getLong(ARG_NOTE_ID) ?: 0L
        val title = arguments?.getString(ARG_NOTE_TITLE) ?: ""
        val text = arguments?.getString(ARG_NOTE_TEXT) ?: ""

        if(id != 0L){
            binding.edTitle.setText(title)
            binding.edText.setText(text)
        }


        binding.toolbarNote.setOnMenuItemClickListener {menuItem ->
            when(menuItem.itemId){
                R.id.menu_save ->{
                    val title = binding.edTitle.text.toString()
                    val text = binding.edText.text.toString()
                    if(text.isBlank()){
                        Toast.makeText(context, context?.getString(R.string.empty_text_error), Toast.LENGTH_SHORT).show()
                    }else{
                        viewModel.saveNote(id, title, text, "test ", "test")
                        changeFragment()
                    }
                    true
                }
                R.id.menu_delete ->{
                    if(id != 0L){
                    viewModel.delete(id)
                        changeFragment()}
                    else{changeFragment()}
                    true
                }
                else -> false
            }
        }


        return binding.root
    }

    private fun changeFragment(){
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, NotesListFragment())
            .commit()
    }

}