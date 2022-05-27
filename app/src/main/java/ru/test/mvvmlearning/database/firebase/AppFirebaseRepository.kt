package ru.test.mvvmlearning.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.test.mvvmlearning.database.DatabaseRepository
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.*

class AppFirebaseRepository : DatabaseRepository {


    //присоздании класса this repo сразу инициализируется значение AUTH (для реализации принципа DRY)
    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        Log.d(TAG, "repo insert")
        val idNote = RFF_DATABASE.push().key.toString() // генерирует ключ записи вроде как
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text
        Log.d(TAG, "insert ${mapNote.values.toString()}")
        RFF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener {
                Log.d(TAG, "repo insert Success")
                onSuccess()
            }
            .addOnFailureListener {
                Log.d(TAG, "repo insert Fail")
                showToast(it.message.toString())
            }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {

    }

    override fun connectToFirebase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
        //во избедание дублирования кода
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        RFF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun signOutFirebase() {
        AUTH.signOut()
    }
}