package com.yvnzhdeh.filmsmercadon.data.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yvnzhdeh.filmsmercadon.model.domain.Film

@Dao
interface FilmDAO {

    @Query("SELECT * FROM Films")
    fun getAllFilms(): List<Film>

    @Insert
    fun insertAllFilms(films: List<Film>)
}