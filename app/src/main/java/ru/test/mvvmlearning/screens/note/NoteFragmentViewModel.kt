package ru.test.mvvmlearning.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.REPOSITORY

class NoteFragmentViewModel(application: Application) : AndroidViewModel(application) {

    fun delete(note: AppNote, onSuccess: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.delete(note) {
            launch(Dispatchers.Main) {
                onSuccess()
            }
        }
    }
}