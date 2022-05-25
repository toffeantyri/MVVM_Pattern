package ru.test.mvvmlearning.screens.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_new_note.*
import kotlinx.android.synthetic.main.fragment_main.*

import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.utilits.APP_ACTIVITY


class MainFragment : Fragment() {

    private lateinit var mViewModel : MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        initialisation()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initialisation(){
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        btn_add_new_note.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewNote)

        }
    }



}
