package com.yvnzhdeh.filmsmercadon.data.usecases

import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private var filmRepository: FilmRepository)
{
    suspend fun getAllFilms(): List<Film>?
    {
        return filmRepository.getFilms()
    }
}