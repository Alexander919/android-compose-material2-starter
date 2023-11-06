package com.hfad.introtocompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hfad.introtocompose.model.DateConverter
import com.hfad.introtocompose.model.Note
import com.hfad.introtocompose.model.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
//@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}