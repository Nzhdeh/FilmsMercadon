package com.yvnzhdeh.filmsmercadon.data.usecases

import android.content.Context
import com.yvnzhdeh.filmsmercadon.data.repositories.DeleteRoomDBRepository

class DeleteRoomDBUseCase {
    suspend fun deleteRoomDB(context: Context)
    {
        val deleteRoomDBRepository: DeleteRoomDBRepository = DeleteRoomDBRepository()
        deleteRoomDBRepository.deleteRoomDB(context)
    }
}