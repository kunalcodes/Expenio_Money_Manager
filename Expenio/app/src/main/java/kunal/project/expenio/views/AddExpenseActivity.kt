package kunal.project.expenio.views

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_expense.*
import kotlinx.android.synthetic.main.activity_add_income.*
import kunal.project.expenio.R
import kunal.project.expenio.models.remote.requests.CreateExpenseRequestModel
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class AddExpenseActivity : AppCompatActivity() {

    val viewModel: MoneyViewModel by viewModels()
    var category: String? = null
    var token : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        token = PreferenceHelper.getStringFromPreference(this@AddExpenseActivity, "token")
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        btnAddExpenseFood.setOnClickListener {
            category = "food"
            btnAddExpenseFood.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
            btnAddExpenseentertainment.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseShopping.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseTransport.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
        }
        btnAddExpenseentertainment.setOnClickListener {
            category = "entertainment"
            btnAddExpenseFood.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseentertainment.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
            btnAddExpenseShopping.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseTransport.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
        }
        btnAddExpenseShopping.setOnClickListener {
            category = "shopping"
            btnAddExpenseFood.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseentertainment.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseShopping.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
            btnAddExpenseTransport.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
        }
        btnAddExpenseTransport.setOnClickListener {
            category = "transport"
            btnAddExpenseFood.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseentertainment.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseShopping.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.grey)))
            btnAddExpenseTransport.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.black)))
        }
        btnAddExpenseApply.setOnClickListener {
            if (isTitleValid() && isDescriptionValid() && isAmountValid() && isCategoryValid()) {
                val title = etAddExpenseTitle.text.toString()
                val desc = etAddExpenseDescription.text.toString()
                val category = category
                val amount = etAddExpenseAmount.text.toString().toInt()
                val createExpenseRequestModel =
                    CreateExpenseRequestModel(title, "$desc*#*#*expense*#*#*$category", amount)
                viewModel.addNewTransaction(token!!, createExpenseRequestModel)
                finish()
            }
        }
        btnAddExpenseBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun isTitleValid(): Boolean {
        return if (etAddExpenseTitle.text.toString().isNotEmpty()) {
            true
        } else {
            etAddExpenseTitle.error = "Title can't be empty!"
            false
        }
    }

    private fun isDescriptionValid(): Boolean {
        return if (etAddExpenseDescription.text.toString().isNotEmpty()) {
            true
        } else {
            etAddExpenseDescription.error = "Enter a valid description!"
            false
        }
    }

    private fun isAmountValid(): Boolean {
        return if (etAddExpenseAmount.text.toString().isNotEmpty()) {
            true
        } else {
            etAddExpenseAmount.error = "Amount can't be empty!"
            false
        }
    }

    private fun isCategoryValid(): Boolean {
        return if (category != null) {
            true
        } else {
            Toast.makeText(this@AddExpenseActivity, "Select a category", Toast.LENGTH_SHORT).show()
            false
        }
    }
}