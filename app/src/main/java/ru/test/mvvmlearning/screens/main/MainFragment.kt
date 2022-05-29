package ru.test.mvvmlearning.screens.main


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*

import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.utilits.APP_ACTIVITY
import ru.test.mvvmlearning.utilits.AppPreference


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

        setHasOptionsMenu(true)
        mAdapter = MainAdapter()
        mRecyclerView = rv_main
        mRecyclerView.adapter = mAdapter

        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        mViewModel.allNotes.observe(this, mObserverList)

        /*ниже то же самое кратче*/
        /*
        mViewModel.allNotes.observe(this){
            val rList = it.asReversed()
            mAdapter.setList(rList)
        }
        */
        btn_add_new_note.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNote)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                mViewModel.signOut()
                AppPreference.setInitUser(false)
                    APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)

            }
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        fun clickItem(note: AppNote) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }



}


