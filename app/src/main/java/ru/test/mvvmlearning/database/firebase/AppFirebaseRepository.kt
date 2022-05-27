package ru.test.mvvmlearning.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import ru.test.mvvmlearning.database.DatabaseRepository
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.EMAIL
import ru.test.mvvmlearning.utilits.PASSWORD

class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {

    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {

    }

    override fun connectToFirebase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }

    }

    override fun signOutFirebase() {
        mAuth.signOut()
    }
}