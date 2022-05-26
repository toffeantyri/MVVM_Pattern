package ru.test.mvvmlearning.database

import androidx.lifecycle.LiveData
import ru.test.mvvmlearning.model.AppNote

interface DatabaseRepository {

    val allNotes : LiveData<List<AppNote>>

    suspend fun insert(note : AppNote, onSuccess: suspend () -> Unit)

    suspend fun delete(note : AppNote, onSuccess: suspend () -> Unit)

}