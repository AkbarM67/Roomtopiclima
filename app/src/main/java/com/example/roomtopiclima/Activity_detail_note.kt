package com.example.roomtopiclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomtopiclima.databinding.ActivityDetailNoteBinding
import com.example.roomtopiclima.room.DataNote

class Activity_detail_note : AppCompatActivity() {

    lateinit var binding : ActivityDetailNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDetail = intent.getSerializableExtra("detail") as DataNote

        binding.detailTitle.text = getDetail.title
        binding.detailNote.text = getDetail.content

    }
}