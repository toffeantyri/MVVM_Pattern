package ru.test.mvvmlearning.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.test.mvvmlearning.utilits.REPOSITORY

class MainFragmentViewModel(application : Application) : AndroidViewModel(application) {

    val allNotes = REPOSITORY.allNotes

    fun signOut(){
        REPOSITORY.signOutFirebase()
    }

}