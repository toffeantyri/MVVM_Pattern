package ru.test.mvvmlearning.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.test.mvvmlearning.database.firebase.AppFirebaseRepository
import ru.test.mvvmlearning.database.room.AppRoomDatabase
import ru.test.mvvmlearning.database.room.AppRoomRepository
import ru.test.mvvmlearning.utilits.*

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToFirebase({ onSuccess() }, { showToast(it) })
            }
        }
    }
}