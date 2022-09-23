package com.example.roomtopiclima.room

import androidx.room.*
import com.example.roomtopiclima.room.DataNote

@Dao
interface NoteDAO {

    @Insert
    fun inserNote(note: DataNote)

    @Query("SELECT * FROM Datanote ORDER BY id DESC")
    fun getDataNote() : List<DataNote>

    @Delete
    fun deleteNote(note: DataNote)

    @Update
    fun updateNote(note: DataNote)
}