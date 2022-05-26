package ru.test.mvvmlearning.screens.addnewnote


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_new_note.*

import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.APP_ACTIVITY
import ru.test.mvvmlearning.utilits.TAG
import ru.test.mvvmlearning.utilits.showToast


class AddNewNote : Fragment() {

    lateinit var mViewModel: AddNewNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_new_note, container, false)
    }

    override fun onStart() {
        super.onStart()
        initialisation()

    }

    private fun initialisation() {
        mViewModel = ViewModelProvider(this).get(AddNewNoteViewModel::class.java)
        btn_add_save_note.setOnClickListener {
            val name = input_name_note.text.toString()
            val text = input_text_note.text.toString()
            Log.d(TAG, name + text)
            if (name.isEmpty()) {
                showToast(getString(R.string.toast_enter_name))
            } else {
                    mViewModel.insert(AppNote(name = name, text = text)){
                        APP_ACTIVITY.navController.navigate(R.id.action_addNewNote_to_mainFragment)
                    }
            }
        }
    }
}
