package com.example.roomtopiclima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomtopiclima.databinding.ActivityMainBinding
import com.example.roomtopiclima.room.DataNote
import com.example.roomtopiclima.room.NoteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var NoteDB : NoteDatabase? = null
    private lateinit var adapterNote : NoteAdapter
    private lateinit var noteViewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NoteDB = NoteDatabase.getInstance(this)

        noteVm()

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        noteViewModel.getAllNoteObservers().observe(this) {
            adapterNote.setNoteData(it as ArrayList<DataNote>)
        }


        binding.btnAddNote.setOnClickListener{
            startActivity(Intent(this, Activity_add_note::class.java))
        }

    }

    private fun noteVm(){
        adapterNote = NoteAdapter(ArrayList())
        binding.TvNote.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.TvNote.adapter = adapterNote
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllNote(){

        GlobalScope.launch {
            val data = NoteDB?.noteDao()?.getDataNote()
            runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.TvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.TvNote.adapter = adapterNote
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            val data = NoteDB?.noteDao()?.getDataNote()
            runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.TvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.TvNote.adapter = adapterNote
            }
        }
    }
}







