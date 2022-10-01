package com.yvnzhdeh.filmsmercadon.data.services

import androidx.room.*
import com.yvnzhdeh.filmsmercadon.model.domain.Film

@Dao
interface FilmDAO {

    @Query("SELECT * FROM Films")
    fun getAllFilms(): List<Film>

    @Insert
    fun insertAllFilms(films: List<Film>)

    @Update
    suspend fun updateFilm(film: Film)

    @Query("DELETE FROM Films")
    suspend fun deleteAll()
}