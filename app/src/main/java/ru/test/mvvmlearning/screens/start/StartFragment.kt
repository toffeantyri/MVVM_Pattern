package ru.test.mvvmlearning.screens.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_start.*
import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.utilits.APP_ACTIVITY
import ru.test.mvvmlearning.utilits.TYPE_ROOM


class StartFragment : Fragment() {


    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onStart() {
        super.onStart()
        initialisation()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun initialisation() {
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        btn_room.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM)
            APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
        }

    }


}
