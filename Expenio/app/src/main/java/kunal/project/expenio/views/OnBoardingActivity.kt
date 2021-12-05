package kunal.project.expenio.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kunal.project.expenio.R

class OnBoardingActivity : AppCompatActivity() {

    lateinit var myIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        setClicklistenersOnViews()
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