package kunal.project.expenio.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kunal.project.expenio.R
import kunal.project.expenio.utils.PreferenceHelper

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var userName : String
    private lateinit var token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        token = PreferenceHelper.getStringFromPreference(this@MainActivity, "token").toString()
        userName = PreferenceHelper.getStringFromPreference(this@MainActivity, "email").toString()
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        tvHomeUser.text = userName
        tvHomeTotalBalance.text = "₹25,000"
    }
}