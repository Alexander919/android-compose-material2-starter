package com.hfad.introtocompose.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.introtocompose.data.NoteData
import com.hfad.introtocompose.model.Note
import com.hfad.introtocompose.repo.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repo: NoteRepo): ViewModel() {
//    private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
//        noteList.addAll(NoteData.loadNotes())
        viewModelScope.launch(context = Dispatchers.IO) {
            repo.getAllNotes().distinctUntilChanged()
                .collect {
                    if(it.isEmpty()) {
                        Log.d("Empty", "Empty list")
                    } else {
                        _noteList.value = it
                    }
                }
        }
    }
    fun addNote(note: Note) = viewModelScope.launch {
        repo.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repo.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repo.deleteNote(note)
    }
}