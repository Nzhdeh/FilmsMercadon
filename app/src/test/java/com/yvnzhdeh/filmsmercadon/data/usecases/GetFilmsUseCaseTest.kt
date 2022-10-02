package com.yvnzhdeh.filmsmercadon.data.usecases

import com.google.gson.Gson
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import com.yvnzhdeh.filmsmercadon.model.domain.Films
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetFilmsUseCaseTest
{
    private val loader = javaClass.classLoader!!

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
        val jsonString = String(loader.getResourceAsStream("films.json")!!.readBytes())
        val myList = Gson().fromJson(jsonString, Films::class.java)

        //given
        coEvery { filmRepository.getFilms() } returns myList.listFilms

        //when
        val response = filmRepository.getFilms()

        //then
        coVerify() {
            filmRepository.getFilms()
        }

        assert(myList.listFilms == response)
    }


}