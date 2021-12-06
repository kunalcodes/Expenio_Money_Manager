package kunal.project.expenio.views

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_income.*
import kotlinx.android.synthetic.main.activity_login.*
import kunal.project.expenio.R
import kunal.project.expenio.models.remote.requests.CreateExpenseRequestModel
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class AddIncomeActivity : AppCompatActivity() {

    val viewModel: MoneyViewModel by viewModels()
    var category: String? = null
    var token : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)
        token = PreferenceHelper.getStringFromPreference(this@AddIncomeActivity, "token")
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        btnAddIncomeCash.setOnClickListener {
            category = "cash"
            btnAddIncomeCash.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
            btnAddIncomeSalary.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddIncomeRent.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
        }
        btnAddIncomeSalary.setOnClickListener {
            category = "salary"
            btnAddIncomeCash.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddIncomeSalary.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
            btnAddIncomeRent.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
        }
        btnAddIncomeRent.setOnClickListener {
            category = "rent"
            btnAddIncomeCash.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddIncomeSalary.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddIncomeRent.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
        }
        btnAddIncomeApply.setOnClickListener {
            if (isTitleValid() && isDescriptionValid() && isAmountValid() && isCategoryValid()) {
                val title = etAddIncomeTitle.text.toString()
                val desc = etAddIncomeDescription.text.toString()
                val category = category
                val amount = etAddIncomeAmount.text.toString().toInt()
                val createExpenseRequestModel =
                    CreateExpenseRequestModel(title, "$desc*#*#*income*#*#*$category", amount)
                viewModel.addNewTransaction(token!!, createExpenseRequestModel)
                finish()
            }
        }
        btnAddIncomeBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun isTitleValid(): Boolean {
        return if (etAddIncomeTitle.text.toString().isNotEmpty()) {
            true
        } else {
            etAddIncomeTitle.error = "Title can't be empty!"
            false
        }
    }

    private fun isDescriptionValid(): Boolean {
        return if (etAddIncomeDescription.text.toString().isNotEmpty()) {
            true
        } else {
            etAddIncomeDescription.error = "Enter a valid description!"
            false
        }
    }

    private fun isAmountValid(): Boolean {
        return if (etAddIncomeAmount.text.toString().isNotEmpty()) {
            true
        } else {
            etAddIncomeAmount.error = "Amount can't be empty!"
            false
        }
    }

    private fun isCategoryValid(): Boolean {
        return if (category != null) {
            true
        } else {
            Toast.makeText(this@AddIncomeActivity, "Select a category", Toast.LENGTH_SHORT).show()
            false
        }
    }

}