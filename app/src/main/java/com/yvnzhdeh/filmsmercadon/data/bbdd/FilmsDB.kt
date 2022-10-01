package com.yvnzhdeh.filmsmercadon.data.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yvnzhdeh.filmsmercadon.data.services.FilmDAO
import com.yvnzhdeh.filmsmercadon.model.domain.Film

@Database(entities = [Film::class], version = 1)
abstract class FilmsDB : RoomDatabase() {
    abstract fun filmDao(): FilmDAO
}