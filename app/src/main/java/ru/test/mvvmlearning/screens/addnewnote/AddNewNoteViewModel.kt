package ru.test.mvvmlearning.screens.addnewnote

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.REPOSITORY
import ru.test.mvvmlearning.utilits.TAG

class AddNewNoteViewModel(application: Application) : AndroidViewModel(application) {

    fun insert(note: AppNote, onSuccess: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "vm insert")
        REPOSITORY.insert(note) {
            launch(Dispatchers.Main) {
                Log.d(TAG, "vm success")
                onSuccess()
            }
        }
    }
}