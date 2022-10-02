package com.yvnzhdeh.filmsmercadon.ui.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.*
import com.yvnzhdeh.filmsmercadon.data.usecases.*
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel (private val getFilmsUseCase: GetFilmsUseCase,
                             private val saveListFilmsInRoomUseCase: SaveListFilmsInRoomUseCase,
                             private val getFilmsRoomUseCase: GetFilmsRoomUseCase ): ViewModel()
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
    @SuppressLint("StaticFieldLeak")
    var context: Context? = null
    var isFirstExecution = true
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
            listsAllFilms.value?.let {
                saveListFilmsInRoomUseCase.saveListFilmsInRoom(it,context!!)
                getFilmsRoom()
            }
        }
    }


    fun getFilmsRoom()
    {
        viewModelScope.launch(Dispatchers.IO){
            val list = getFilmsRoomUseCase.getListFilmsInRoom(context!!)
            listsAllFilmsRoom.postValue(list)
        }
    }

    fun changeItemClicked(itemClicked: Film)
    {
        this.itemListFilmsClicked.value = itemClicked
    }

    fun deleteBBDD()
    {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteRoomDBUseCase = DeleteRoomDBUseCase()
            deleteRoomDBUseCase.deleteRoomDB(context!!)
            listsAllFilmsRoom.postValue(listOf())
        }

    }

    fun updateBBDD(){
        getAllFilms()
    }

    fun updateFilm(selectedFilm: Film){
        viewModelScope.launch(Dispatchers.IO) {
            val updateFilmSelectedUseCase = UpdateFilmSelectedUseCase()
            updateFilmSelectedUseCase.updateFilmSelected(context!!, selectedFilm)
        }
    }
}