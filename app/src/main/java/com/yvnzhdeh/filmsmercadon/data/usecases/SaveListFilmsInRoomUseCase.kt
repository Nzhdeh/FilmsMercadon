package com.yvnzhdeh.filmsmercadon.data.usecases

import android.content.Context
import com.yvnzhdeh.filmsmercadon.data.repositories.SaveListFilmsInRoomRepository
import com.yvnzhdeh.filmsmercadon.model.domain.Film

class SaveListFilmsInRoomUseCase(private val saveListFilmsInRoomRepository: SaveListFilmsInRoomRepository) {

    fun saveListFilmsInRoom(films: List<Film>, context: Context)
    {
        return saveListFilmsInRoomRepository.saveListFilmsInRoom(films, context)
    }
}