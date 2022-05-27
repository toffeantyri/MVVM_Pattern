package ru.test.mvvmlearning.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.RFF_DATABASE

class AllNoteLiveData : LiveData<List<AppNote>>() {


    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNote::class.java) ?: AppNote()
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }

    //если Livedata активна (когда фрагмент активен?)
    override fun onActive() {
       RFF_DATABASE.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        RFF_DATABASE.removeEventListener(listener)
        super.onInactive()
    }
}