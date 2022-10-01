package com.yvnzhdeh.filmsmercadon.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel (private val getFilmsUseCase: GetFilmsUseCase): ViewModel()
{
    class Factory @Inject constructor(private val getFilmsUseCase: GetFilmsUseCase): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create (modelClass: Class<T>): T {
            return MainActivityViewModel(getFilmsUseCase) as T
        }
    }

    val listsAllFilms: MutableLiveData<List<Film>> = MutableLiveData(listOf())


    init {
        getAllFilms()
    }

    private fun getAllFilms(): List<Film>?
    {
        var list: List<Film>? = listOf()
        viewModelScope.launch(Dispatchers.IO) {
            list = getFilmsUseCase.getAllFilms()
            Log.d("","$list")
            listsAllFilms.postValue(list)
        }
        return list
    }

    private fun saveFilmsInRoom(listFilms: List<Film>)
    {

    }
}