package com.hfad.introtocompose.screen

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfad.introtocompose.R
import com.hfad.introtocompose.components.NoteButton
import com.hfad.introtocompose.components.NoteInputText
import com.hfad.introtocompose.data.NoteData
import com.hfad.introtocompose.model.Note
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "icon")
            },
            backgroundColor = Color(0xffdadfe3)
        )
        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title, label = "Title", onTextChange = { inputText ->
                    if(inputText.all { it.isLetter() || it.isWhitespace() }) {
                        title = inputText
                    }
                }
            )
            NoteInputText(
                text = description, label = "Description", onTextChange = { inputText ->
                    if(inputText.all { it.isLetter() || it.isWhitespace() }) {
                        description = inputText
                    }
                }
            )
            NoteButton(text = "Save", onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(Note(title = title, description = description))
                    //clear fields
                    title = ""
                    description = ""
                    //toast
                    Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
                }
            })
        }
        Divider()
        LazyColumn {
            items(notes) {
                NoteRow(note = it, onNoteClicked = { note ->
                    onRemoveNote(note)
                })
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xffdfe6eb),
        elevation = 6.dp
    ) {
        Column(
            modifier = modifier
                .clickable { onNoteClicked.invoke(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.entryDate.time), style = MaterialTheme.typography.caption)
        }
    }
}

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa", Locale.getDefault())

    return format.format(date)
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes = NoteData.loadNotes(), onAddNote = {}, onRemoveNote = {})
}