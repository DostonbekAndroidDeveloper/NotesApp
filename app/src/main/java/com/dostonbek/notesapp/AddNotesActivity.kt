package com.dostonbek.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dostonbek.notesapp.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var database: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseHelper(this)
        binding.saveBtn.setOnClickListener {
            val title = binding.title.text.toString()
            val detail = binding.details.text.toString()
            if (title.isNotEmpty()||detail.isNotEmpty()){
                val note = Note(0, title, detail)
                database.insertNote(note)
                finish()
                Toast.makeText(this, "Note is added successfully", Toast.LENGTH_SHORT).show()

            }
            else {
                Toast.makeText(this,"Space is empty", Toast.LENGTH_SHORT).show()
            }
            }
    }
}