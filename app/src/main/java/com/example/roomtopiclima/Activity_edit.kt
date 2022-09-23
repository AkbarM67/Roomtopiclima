package com.example.roomtopiclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomtopiclima.databinding.ActivityEditBinding
import com.example.roomtopiclima.room.DataNote
import com.example.roomtopiclima.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Activity_edit : AppCompatActivity() {

    lateinit var binding : ActivityEditBinding
    var dbNote: NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbNote = NoteDatabase.getInstance(this)

        //        ambil data yg dikirim dari adapter
        var getDataNote = intent.getSerializableExtra("dataedit") as DataNote
//        set data yang dikirim ke dalam editText
        binding.editTitle.setText(getDataNote.title)
        binding.editNotee.setText(getDataNote.content)
        binding.idNote.setText(getDataNote.id.toString())

        //        klik edit, data akan diedit
        binding.btnEditNote.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        var idNote = binding.idNote.text.toString().toInt()
        var title = binding.editTitle.text.toString()
        var note = binding.editNotee.text.toString()


        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(DataNote(idNote, title, note))
            runOnUiThread {
                Toast.makeText(this@Activity_edit, "Dat berhasil di Edit", Toast.LENGTH_LONG)
                    .show()
            }
            finish()
        }
    }
}