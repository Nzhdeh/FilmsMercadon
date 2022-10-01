package com.yvnzhdeh.filmsmercadon.data.repositories

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.yvnzhdeh.filmsmercadon.data.bbdd.FilmsDB
import com.yvnzhdeh.filmsmercadon.model.domain.Film

class SaveListFilmsInRoomRepository {
    fun saveListFilmsInRoom(films: List<Film>, context: Context)
    {
        val db = Room.databaseBuilder(
            context,
            FilmsDB::class.java, "FilmsDB"
        ).build()

        try {
            db.filmDao().insertAllFilms(films)
        }catch (e: Exception)
        {
            Log.i("saveListFilmsInRoom",e.toString())
        }

    }
}