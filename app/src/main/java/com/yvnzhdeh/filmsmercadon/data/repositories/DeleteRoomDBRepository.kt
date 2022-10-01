package com.yvnzhdeh.filmsmercadon.data.repositories

import android.content.Context
import androidx.room.Room
import com.yvnzhdeh.filmsmercadon.data.bbdd.FilmsDB

class DeleteRoomDBRepository {
    suspend fun deleteRoomDB(context: Context)
    {
        val db = Room.databaseBuilder(
            context.applicationContext,
            FilmsDB::class.java, "FilmsDB"
        ).build()
        db.filmDao().deleteAll()
    }
}