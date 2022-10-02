package com.yvnzhdeh.filmsmercadon.data.usecases

import android.content.Context
import com.yvnzhdeh.filmsmercadon.data.repositories.UpdateFilmSelectedRepository
import com.yvnzhdeh.filmsmercadon.model.domain.Film

class UpdateFilmSelectedUseCase() {
    suspend fun updateFilmSelected(context: Context, selectedFilm: Film)
    {
        val updateFilmSelectedRepository = UpdateFilmSelectedRepository()
        updateFilmSelectedRepository.updateFilmSelected(context, selectedFilm)
    }
}