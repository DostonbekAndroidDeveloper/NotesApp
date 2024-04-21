package com.dostonbek.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dostonbek.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database:DatabaseHelper
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= DatabaseHelper(this)
        adapter= NotesAdapter(database.getAllNotes(), this)
        binding.recyclerview.adapter=adapter




        binding.addBtn.setOnClickListener {
            val intent:Intent=Intent(this,AddNotesActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        adapter.refreshData(database.getAllNotes())
    }
}