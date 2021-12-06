package kunal.project.expenio.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kunal.project.expenio.R
import kunal.project.expenio.models.remote.requests.CreateExpenseRequestModel
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class AddIncomeActivity : AppCompatActivity() {

    val viewModel : MoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)
        val token = PreferenceHelper.getStringFromPreference(this@AddIncomeActivity, "token")
        val createExpenseRequestModel =
            CreateExpenseRequestModel("title", "desc*#*#*income*#*#*cash", 400)
        viewModel.addNewTransaction(token!!, createExpenseRequestModel)
        finish()
    }
}