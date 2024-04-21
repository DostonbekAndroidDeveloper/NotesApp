package com.dostonbek.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME="newNotesApp.db"
        private const val DATABASE_VERSION=7
        private const val TABLE_NAME="allNotes"
        private const val COLUMN_ID="id"
        private const val COLUMN_TITLE="title"
        private const val COLUMN_DETAILS="details"




    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(db: SQLiteDatabase?) {
      val tableQuery="CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_TITLE TEXT, $COLUMN_DETAILS TEXT)"
        db?.execSQL(tableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery="DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)

    }
    fun insertNote(note: Note){
        val database=writableDatabase
        val values=ContentValues().apply {
            put(COLUMN_TITLE,note.title)
            put(COLUMN_DETAILS,note.detail)
        }
        database.insert(TABLE_NAME,null,values)
        database.close()
    }
    fun getAllNotes():List<Note>{
        val notesList= mutableListOf<Note>()
        val database=readableDatabase
        val query ="SELECT * FROM $TABLE_NAME"
        val cursor=database.rawQuery(query,null)
        while (cursor.moveToNext()){


            val id =cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val detail=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS))
            val note=Note(id,title,detail)
            notesList.add(note)


    }
    cursor.close()
    database.close()
    return notesList
    }
    fun editNote(note: Note){
        val database=writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,note.title)
            put(COLUMN_DETAILS,note.detail)
        }
        val whereClause="$COLUMN_ID = ?"
        val whereArgs= arrayOf(note.id.toString())
        database.update(TABLE_NAME,values,whereClause,whereArgs)
        database.close()
    }
    fun getNoteById(noteId:Int):Note{
        val database=writableDatabase
        val query="SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=$noteId"
        val cursor=database.rawQuery(query,null)
        cursor.moveToFirst()
        val id=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val details=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS))
        cursor.close()
        database.close()
        return Note(id,title, details)
    }
    fun deleteNote(noteId:Int){
        val database=writableDatabase
        val whereClause="$COLUMN_ID = ?"
        val whereArgs= arrayOf(noteId.toString())
        database.delete(TABLE_NAME,whereClause,whereArgs)
        database.close()
    }


}