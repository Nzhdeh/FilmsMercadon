package com.yvnzhdeh.filmsmercadon.data.repositories

import com.yvnzhdeh.filmsmercadon.data.services.FilmsService
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class FilmRepository
{
    private lateinit var filmsService: FilmsService

    suspend fun getFilms(): List<Film>?
    {
        var listAllFilms: List<Film>? = listOf()
        try {
            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl("https://ghibliapi.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()

            filmsService = retrofit.create(FilmsService::class.java)

            val call: Response<List<Film>> = filmsService.getFilms()

            if (call.isSuccessful)
            {
                listAllFilms = call.body()
            }
        }catch (e: Exception)
        {
            return listAllFilms
        }
        return listAllFilms
    }
}