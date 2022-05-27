package ru.test.mvvmlearning.screens.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_start.*
import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.utilits.*


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
            mViewModel.initDatabase(TYPE_ROOM) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
        btn_firebase.setOnClickListener {
            input_email.visibility = View.VISIBLE
            input_password.visibility = View.VISIBLE
            btn_login.visibility = View.VISIBLE
            btn_login.setOnClickListener {
                val inputEmail = input_email.text.toString()
                val inputPassword = input_password.text.toString()
                if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    mViewModel.initDatabase(TYPE_FIREBASE) {
                        showToast("init successful")
                        //APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else {
                    showToast(getString(R.string.mail_password_error))
                }
            }
        }
    }
}
