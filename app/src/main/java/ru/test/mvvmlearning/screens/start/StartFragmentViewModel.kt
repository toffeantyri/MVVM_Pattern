package ru.test.mvvmlearning.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.test.mvvmlearning.database.room.AppRoomDatabase
import ru.test.mvvmlearning.database.room.AppRoomRepository
import ru.test.mvvmlearning.utilits.APP_ACTIVITY
import ru.test.mvvmlearning.utilits.REPOSITORY
import ru.test.mvvmlearning.utilits.TYPE_ROOM

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}