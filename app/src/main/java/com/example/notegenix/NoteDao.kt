package com.example.notegenix

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //ignores a new text if it's exactly the same as one already in the list
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Select * from notes_table order by id")
    fun getAllNotes(): List<Note>
}