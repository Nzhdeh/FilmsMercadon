package com.yvnzhdeh.filmsmercadon.data.repositories

import android.content.Context
import androidx.room.Room
import com.yvnzhdeh.filmsmercadon.data.bbdd.FilmsDB
import com.yvnzhdeh.filmsmercadon.model.domain.Film

class GetFilmsRoomRepository {

    fun getListFilmsInRoom(context: Context): List<Film> {
        val db = Room.databaseBuilder(
            context.applicationContext,
            FilmsDB::class.java, "FilmsDB"
        ).build()
        val lists = db.filmDao().getAllFilms()
        return lists
    }
}