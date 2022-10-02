package com.yvnzhdeh.filmsmercadon.data.usecases

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Paths


internal class GetFilmsUseCaseTest
{
    @MockK
    private lateinit var filmRepository: FilmRepository

    lateinit var getFilmsUseCase: GetFilmsUseCase
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getFilmsUseCase = GetFilmsUseCase(filmRepository)
    }

    @Test
    fun checkThatTheApiReturnsAListOfFilms() = runBlocking {
        val jsonString = String(Files.readAllBytes(Paths.get("D:\\WorkMercadona\\app\\src\\test\\java\\com\\yvnzhdeh\\filmsmercadon\\data\\usecases\\films.json")))
        val type: Type = object : TypeToken<List<Film?>?>() {}.type
        val myList: List<Film> = Gson().fromJson(jsonString, type)

        //given
        coEvery { filmRepository.getFilms() } returns myList

        //when
        val response = filmRepository.getFilms()

        //then
        coVerify() {
            filmRepository.getFilms()
        }

        assert(myList == response)
    }


}