package ru.test.mvvmlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import ru.test.mvvmlearning.utilits.APP_ACTIVITY

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APP_ACTIVITY = this // что бы получать контекст активити в любом месте приложения
        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment)

        mToolbar = main_toolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)

    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
