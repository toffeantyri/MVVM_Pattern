package ru.test.mvvmlearning.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import ru.test.mvvmlearning.model.AppNote

class AllNoteLiveData : LiveData<List<AppNote>>() {

    private val mAuth = FirebaseAuth.getInstance()
    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())


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
        mDatabaseReference.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        mDatabaseReference.removeEventListener(listener)
        super.onInactive()
    }
}