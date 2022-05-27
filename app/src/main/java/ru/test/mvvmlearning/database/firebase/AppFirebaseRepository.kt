package ru.test.mvvmlearning.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.test.mvvmlearning.database.DatabaseRepository
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.*

class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        Log.d(TAG, "repo insert")
        val idNote = mDatabaseReference.push().key.toString() // генерирует ключ записи вроде как
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        Log.d(TAG, "insert ${mapNote.values.toString()}")
        mDatabaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener {  Log.d(TAG, "repo insert Success")
                onSuccess() }
            .addOnFailureListener { Log.d(TAG, "repo insert Fail")
                showToast(it.message.toString()) }
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