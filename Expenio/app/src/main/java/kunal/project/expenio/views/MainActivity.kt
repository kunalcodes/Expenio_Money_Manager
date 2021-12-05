package kunal.project.expenio.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kunal.project.expenio.R
import kunal.project.expenio.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val token = PreferenceHelper.getStringFromPreference(this@MainActivity, "token")
        tvText.text = token
    }
}