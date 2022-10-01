package com.yvnzhdeh.filmsmercadon.data.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yvnzhdeh.filmsmercadon.model.domain.Film

@Dao
interface FilmDAO {

    @Query("SELECT * FROM Films")
    fun getAllFilms(): List<Film>

    @Insert
    fun insertAllFilms(films: List<Film>)

    @Update
    suspend fun updateFilm(film: Film)
}