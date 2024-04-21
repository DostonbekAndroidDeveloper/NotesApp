package com.dostonbek.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dostonbek.notesapp.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private lateinit var database: DatabaseHelper
    private var notesId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        notesId = intent.getIntExtra("notes_id", -1)
        if (notesId == -1) {
            finish()
            return
        }
        val note = database.getNoteById(notesId)
        binding.infoTitle.text = note.title
        binding.infoDetails.text = note.detail

    }
}