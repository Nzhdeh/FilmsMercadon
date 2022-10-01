package com.yvnzhdeh.filmsmercadon.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.model.domain.Film
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
    var itemListFilmsClicked: MutableLiveData<Film> = MutableLiveData()

    init {
        getAllFilms()
    }

    private fun getAllFilms()
    {
        viewModelScope.launch(Dispatchers.IO) {
            listsAllFilms.postValue(getFilmsUseCase.getAllFilms())
        }
    }

    private fun saveFilmsInRoom(listFilms: List<Film>)
    {

    }

    fun changeItemClicked(itemClicked: Film)
    {
        this.itemListFilmsClicked.value = itemClicked
    }
}