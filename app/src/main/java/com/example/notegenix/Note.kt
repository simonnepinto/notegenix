package com.example.notegenix

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//we create a table
@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text")val text:String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}