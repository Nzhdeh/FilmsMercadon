package com.yvnzhdeh.filmsmercadon.data.services

import com.yvnzhdeh.filmsmercadon.model.domain.Film
import retrofit2.Response
import retrofit2.http.GET

interface FilmsService {

    @GET("films")
    suspend fun getFilms(): Response<List<Film>>

}