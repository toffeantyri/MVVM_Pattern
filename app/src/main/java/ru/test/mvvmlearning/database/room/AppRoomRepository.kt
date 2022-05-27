package ru.test.mvvmlearning.database.room

import android.util.Log
import androidx.lifecycle.LiveData
import ru.test.mvvmlearning.database.DatabaseRepository
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.TAG

class AppRoomRepository(private val appRoomDao: AppRoomDao) : DatabaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        Log.d(TAG, "repo insert")
        appRoomDao.insert(note)
        onSuccess()
        Log.d(TAG, "repo onSuccess")
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override fun signOutFirebase() {
        super.signOutFirebase()
    }
}