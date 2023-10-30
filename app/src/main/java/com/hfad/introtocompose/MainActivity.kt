package com.hfad.introtocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.introtocompose.data.NoteData
import com.hfad.introtocompose.model.Note
import com.hfad.introtocompose.screen.NoteScreen
import com.hfad.introtocompose.screen.NoteViewModel
import com.hfad.introtocompose.ui.theme.Material2ThemeStarter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Material2ThemeStarter {
        content()
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val notesList = noteViewModel.getAllNotes()
//    val notesList = remember {
//        mutableStateListOf<Note>()
//    }

    NoteScreen(notes = notesList, onAddNote = {
        noteViewModel.addNote(it)
//        notesList.add(it)
    }, onRemoveNote = {
        noteViewModel.removeNote(it)
//        notesList.remove(it)
    })
}

@Composable
fun MainContent() {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        NotesApp()
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        MainContent()
    }
}
