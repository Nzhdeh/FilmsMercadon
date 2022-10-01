package com.yvnzhdeh.filmsmercadon.data.usecases

import android.content.Context
import com.yvnzhdeh.filmsmercadon.data.repositories.GetFilmsRoomRepository
import com.yvnzhdeh.filmsmercadon.model.domain.Film

class GetFilmsRoomUseCase(private val getFilmsRoomRepository: GetFilmsRoomRepository)
{
    fun getListFilmsInRoom(context: Context): List<Film>
    {
        return getFilmsRoomRepository.getListFilmsInRoom(context)
    }
}