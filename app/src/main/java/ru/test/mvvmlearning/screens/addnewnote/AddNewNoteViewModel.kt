package ru.test.mvvmlearning.screens.addnewnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.REPOSITORY
import kotlin.coroutines.CoroutineContext

class AddNewNoteViewModel(application: Application) : AndroidViewModel(application) {

    fun insert(note: AppNote, onSuccess: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        REPOSITORY.insert(note) {
            withContext(context = Dispatchers.Main){
                onSuccess()
            }
        }
    }

}