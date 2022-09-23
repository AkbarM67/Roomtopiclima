package com.example.roomtopiclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomtopiclima.databinding.ActivityAddNoteBinding
import com.example.roomtopiclima.room.DataNote
import com.example.roomtopiclima.room.NoteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Activity_add_note : AppCompatActivity() {

    lateinit var binding: ActivityAddNoteBinding
    var dbNote: NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbNote = NoteDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            addNote()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun addNote(){
        GlobalScope.async{
            var title = binding.noteTitle.text.toString()
            var note = binding.noteContent.text.toString()

            dbNote!!.noteDao().inserNote(DataNote(0,title,note))
        }
        finish()
    }
}