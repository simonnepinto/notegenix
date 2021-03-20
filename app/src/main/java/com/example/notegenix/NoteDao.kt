package com.example.notegenix

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    //it is necessary to run insert and delete in background thread since they perform I/O
    //if insert/delete run in the main(UI) thread, the app will halt till the operation is completed

    @Insert(onConflict = OnConflictStrategy.IGNORE) //ignores a new text if it's exactly the same as one already in the list
    suspend fun insert(note: Note)  //co-routines to work in background; can only be called from a co-routine or from another suspension function.

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notes_table order by id")
    fun getAllNotes(): LiveData<List<Note>>  //observe database changes
}