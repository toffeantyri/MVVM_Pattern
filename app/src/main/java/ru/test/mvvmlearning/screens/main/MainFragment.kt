package ru.test.mvvmlearning.screens.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_add_new_note.*
import kotlinx.android.synthetic.main.fragment_main.*

import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.APP_ACTIVITY


class MainFragment : Fragment() {

    private lateinit var mViewModel: MainFragmentViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList: Observer<List<AppNote>>

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


    private fun initialisation() {

        mAdapter = MainAdapter()
        mRecyclerView = rv_main
        mRecyclerView.adapter = mAdapter

        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        mViewModel.allNotes.observe(this, mObserverList)
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        /*ниже то же самое кратче*/
        /*
        mViewModel.allNotes.observe(this){
            val rList = it.asReversed()
            mAdapter.setList(rList)
        }
        */
        btn_add_new_note.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewNote)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

}
