package kunal.project.expenio.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kunal.project.expenio.R
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var token: String
    private val viewModel: MoneyViewModel by viewModels()
    private lateinit var userName : String
    private var totalIncome = 0
    private var totalExpense = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        token = PreferenceHelper.getStringFromPreference(this@ProfileActivity, "token").toString()
        userName = PreferenceHelper.getStringFromPreference(this@ProfileActivity, "email").toString()
        viewModel.getTotalIncome().observe(this, Observer {
            it?.let {
                totalIncome = it
                val balance = totalIncome - totalExpense
                tvProfileBalance.text = "₹$balance"
            }
        })
        viewModel.getTotalExpenses().observe(this, Observer {
            it?.let {
                totalExpense = it
                val balance = totalIncome - totalExpense
                tvProfileBalance.text = "₹$balance"
            }
        })
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        tvProfileBack.setOnClickListener {
            onBackPressed()
        }

        tvProfileName.text = userName

        btnProfileDelete.setOnClickListener {
            PreferenceHelper.writeStringToPreference(this@ProfileActivity, "token", "")
            PreferenceHelper.writeStringToPreference(this@ProfileActivity, "email", "")
            viewModel.deleteUser(token)
            finish()
        }

        btnProfileSignout.setOnClickListener {
            PreferenceHelper.writeStringToPreference(this@ProfileActivity, "token", "")
            PreferenceHelper.writeStringToPreference(this@ProfileActivity, "email", "")
            viewModel.logout(token)
            finish()
        }
    }
}