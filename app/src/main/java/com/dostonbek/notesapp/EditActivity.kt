package com.dostonbek.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dostonbek.notesapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var database:DatabaseHelper
    private var noteId:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= DatabaseHelper(this)
        noteId=intent.getIntExtra("note_id",-1)
        if (noteId==-1){
            finish()
            return
        }
        val note=database.getNoteById(noteId)
        binding.editTitle.setText(note.title)
        binding.editDetails.setText(note.detail)

        binding.editBtn.setOnClickListener {
            val newTitle=binding.editTitle.text.toString()
            val newDetail=binding.editDetails.text.toString()
            val updateNote=Note(noteId,newTitle,newDetail)
            database.editNote(updateNote)
            finish()
            Toast.makeText(this,"Changes Saved successfully",Toast.LENGTH_SHORT).show()


        }


    }
}