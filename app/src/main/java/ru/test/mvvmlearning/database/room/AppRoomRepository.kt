package ru.test.mvvmlearning.database.room

import androidx.lifecycle.LiveData
import ru.test.mvvmlearning.database.DatabaseRepository
import ru.test.mvvmlearning.model.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao) : DatabaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: suspend () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess:  suspend () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }
}