package com.dostonbek.notesapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.RouteListingPreference.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
class NotesAdapter(private var notes:List<Note> , context: Context):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val database:DatabaseHelper= DatabaseHelper(context)
    class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var titleTextView:TextView=itemView.findViewById(R.id.itemTitle)
        var detailTextView:TextView=itemView.findViewById(R.id.itemDetails)
        var editButton:ImageView=itemView.findViewById(R.id.item_edit_btn)
        val deleteBtn:ImageView=itemView.findViewById(R.id.item_delete_btn)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int= notes.size



    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=notes[position]
        holder.titleTextView.text=note.title
        holder.detailTextView.text=note.detail

        holder.editButton.setOnClickListener {


            val intent = Intent(holder.itemView.context, EditActivity::class.java).apply {
                putExtra("note_id", note.id)

            }

            holder.itemView.context.startActivity(intent)

        }
        holder.itemView.setOnClickListener{
            val intent=Intent(holder.itemView.context,InfoActivity::class.java).apply {
                putExtra("notes_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteBtn.setOnClickListener {
            database.deleteNote(note.id)
            refreshData(database.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Deleted", Toast.LENGTH_SHORT).show()

        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newNotes:List<Note>){
        notes=newNotes
        notifyDataSetChanged()

    }



}