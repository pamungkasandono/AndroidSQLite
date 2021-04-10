package com.example.android2mynotesapp.helper

import android.database.Cursor
import android.util.Log
import com.example.android2mynotesapp.db.DatabaseContract
import com.example.android2mynotesapp.entity.Note

object MappingHelper {
    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<Note> {
        val noteList = ArrayList<Note>()

        Log.d("asdasd", notesCursor.toString())

        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val description =
                    getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))

                noteList.add(Note(id, title, description, date))
            }
        }
        Log.d("asdasd123", noteList.toString())
        return noteList
    }
}