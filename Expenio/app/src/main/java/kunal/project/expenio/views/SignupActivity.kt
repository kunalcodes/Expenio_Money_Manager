package kunal.project.expenio.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kunal.project.expenio.R
import kunal.project.expenio.models.remote.Resource
import kunal.project.expenio.models.remote.Status
import kunal.project.expenio.models.remote.requests.LoginRequestModel
import kunal.project.expenio.models.remote.requests.SignupRequestModel
import kunal.project.expenio.models.remote.responses.SignupResponseModel
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel
import org.jetbrains.anko.longToast

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private val REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val viewModel : MoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        btnSignupApply.setOnClickListener {
            if (isEmailValid() && isPasswordValid() && doPasswordsMatch()){
                val signupRequestModel = SignupRequestModel("UserName",etSignupEmail.getText().toString(), etSignupPassword.getText().toString(), 30)
                viewModel.userSignup(signupRequestModel).observe(this, Observer {
                    val response = it
                    when (response.status) {
                        Status.SUCCESS -> {
                            signUpUser(response)
                        }
                        Status.ERROR -> {
                            val error = response.message!!
                            longToast("error = $error")
                        }
                        Status.LOADING -> {

                        }
                    }
                })
            }
        }

        tvSignupBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun signUpUser(response: Resource<SignupResponseModel>?) {
        longToast(response?.data?.token.toString())
        PreferenceHelper.writeStringToPreference(this@SignupActivity, "token", response!!.data!!.token)
        PreferenceHelper.writeStringToPreference(this@SignupActivity, "email", response!!.data!!.user!!.email)
        val intent = Intent(this@SignupActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun isPasswordValid(): Boolean {
        return if (etSignupPassword.text.toString().length >= 8) {
            true
        } else {
            etSignupPassword.error = "Password should contain at least 8 characters"
            false
        }
    }

    private fun doPasswordsMatch(): Boolean {
        return if (etSignupPassword.text.toString().equals(etSignupConfirmPassword.text.toString())) {
            true
        } else {
            etSignupConfirmPassword.error = "Passwords should match"
            false
        }
    }

    private fun isEmailValid(): Boolean {
        return if (etSignupEmail.text.toString().isNotEmpty() &&
            etSignupEmail.text.toString().matches(Regex(REGEX))
        ) {
            true
        } else {
            etSignupEmail.error = "Enter a valid email"
            false
        }
    }
}