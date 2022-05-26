package ru.test.mvvmlearning.screens.note

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_note.*

import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.model.AppNote
import ru.test.mvvmlearning.screens.main.MainAdapter
import ru.test.mvvmlearning.screens.main.MainFragmentViewModel
import ru.test.mvvmlearning.utilits.APP_ACTIVITY


class NoteFragment : Fragment() {

    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCurrentNote = arguments?.getSerializable("note") as AppNote
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onStart() {
        super.onStart()
        initialisation()
    }


    private fun initialisation() {
        setHasOptionsMenu(true)
        note_name.text = mCurrentNote.name
        note_text.text = mCurrentNote.text
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote) {
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}


