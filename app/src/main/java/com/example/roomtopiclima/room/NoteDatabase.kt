package com.example.roomtopiclima.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Database::class], version = 1 )
abstract class NoteDatabase: RoomDatabase(){

    abstract fun noteDao() : NoteDAO

    companion object{
        private var INSTANCE : NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase?{
            if (INSTANCE == null){
                synchronized(NoteDatabase::class){
                    INSTANCE = Room.databaseBuilder(/* context = */ context.applicationContext,
                        /* klass = */ NoteDatabase::class.java,/* name = */ "Note.dp").build()
                }
            }
            return INSTANCE
        }

    }
}
