package com.yvnzhdeh.filmsmercadon.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsRoomUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.SaveListFilmsInRoomUseCase
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel (private val getFilmsUseCase: GetFilmsUseCase,
                             private val saveListFilmsInRoomUseCase: SaveListFilmsInRoomUseCase,
                             private val getFilmsRoomUseCase: GetFilmsRoomUseCase
): ViewModel()
{
    class Factory @Inject constructor(private val getFilmsUseCase: GetFilmsUseCase,
                                      private val saveListFilmsInRoomUseCase: SaveListFilmsInRoomUseCase,
                                      private val getFilmsRoomUseCase: GetFilmsRoomUseCase): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create (modelClass: Class<T>): T {
            return MainActivityViewModel(getFilmsUseCase, saveListFilmsInRoomUseCase, getFilmsRoomUseCase) as T
        }
    }

    private val listsAllFilms: MutableLiveData<List<Film>> = MutableLiveData(listOf())
    val listsAllFilmsRoom: MutableLiveData<List<Film>> = MutableLiveData(listOf())
    lateinit var context: Context
    var itemListFilmsClicked: MutableLiveData<Film> = MutableLiveData()


    init {
        getAllFilms()
    }

    private fun getAllFilms()
    {
        viewModelScope.launch(Dispatchers.IO) {
            listsAllFilms.postValue(getFilmsUseCase.getAllFilms())
            if (!listsAllFilms.value.isNullOrEmpty())
            {
                saveFilmsInRoom()
            }
        }
    }

    private fun saveFilmsInRoom()
    {
        viewModelScope.launch(Dispatchers.IO) {
            listsAllFilms.value?.let { saveListFilmsInRoomUseCase.saveListFilmsInRoom(it,context) }
        }
    }


    fun getFilmsRoom()
    {
        viewModelScope.launch(Dispatchers.IO){
            val list = getFilmsRoomUseCase.getListFilmsInRoom(context)
            listsAllFilmsRoom.postValue(list)
        }
    }

    fun changeItemClicked(itemClicked: Film)
    {
        this.itemListFilmsClicked.value = itemClicked
    }
}