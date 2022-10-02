package com.yvnzhdeh.filmsmercadon.data.repositories

import android.content.Context
import androidx.room.Room
import com.yvnzhdeh.filmsmercadon.data.bbdd.FilmsDB
import com.yvnzhdeh.filmsmercadon.model.domain.Film

class UpdateFilmSelectedRepository
{
    suspend fun updateFilmSelected(context: Context, selectedFilm: Film)
    {
        val db = Room.databaseBuilder(
            context.applicationContext,
            FilmsDB::class.java, "FilmsDB"
        ).build()
        db.filmDao().updateFilm(selectedFilm)
    }
}