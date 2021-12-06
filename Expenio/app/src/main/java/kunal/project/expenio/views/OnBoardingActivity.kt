package kunal.project.expenio.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kunal.project.expenio.R
import kunal.project.expenio.utils.PreferenceHelper

class OnBoardingActivity : AppCompatActivity() {

    lateinit var myIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        checkLoginStatus()
        setClicklistenersOnViews()
    }

    override fun onResume() {
        super.onResume()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val token = PreferenceHelper.getStringFromPreference(this@OnBoardingActivity, "token")
        if (token != null) {
            if (token != "") {
                val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setClicklistenersOnViews() {
        btnOnBoardingLogin.setOnClickListener {
            myIntent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
            startActivity(myIntent)
        }
        btnOnBoardingSignup.setOnClickListener {
            myIntent = Intent(this@OnBoardingActivity, SignupActivity::class.java)
            startActivity(myIntent)
        }
    }

}