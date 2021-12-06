package kunal.project.expenio.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_single_transaction.*
import kunal.project.expenio.R
import kunal.project.expenio.models.local.Expense
import kunal.project.expenio.utils.PreferenceHelper
import kunal.project.expenio.viewmodels.MoneyViewModel

@AndroidEntryPoint
class SingleTransactionActivity : AppCompatActivity() {

    private lateinit var token: String
    private val viewModel: MoneyViewModel by viewModels()
    private lateinit var expense: Expense

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_transaction)
        token = PreferenceHelper.getStringFromPreference(this@SingleTransactionActivity, "token")
            .toString()
        val bundle = intent.getBundleExtra("bundle")
        expense = bundle!!.getSerializable("expense") as Expense
        setClicklistenersOnViews()
    }

    private fun setClicklistenersOnViews() {
        btnSingleTransactionBack.setOnClickListener {
            onBackPressed()
        }
        btnSingleTransactionDelete.setOnClickListener {
            viewModel.deleteTransaction(token, expense)
            finish()
        }
        tvSingleTransactionTitle.text = expense.title
        tvSingleTransactionDate.text = getDateWithMonthName(expense.date)
        if (expense.type == "income") {
            tvSingleTransactionAmount.text = "₹${expense.amount}"
        } else {
            tvSingleTransactionAmount.text = "-₹${expense.amount}"
        }
        tvSingleTransactionDescription.text = expense.desc
        var imgId = 0
        if (expense.category == "salary") {
            imgId = R.drawable.ic_salary
        } else if (expense.category == "cash") {
            imgId = R.drawable.ic_cash
        } else if (expense.category == "rent") {
            imgId = R.drawable.ic_hose_rent
        } else if (expense.category == "shopping") {
            imgId = R.drawable.ic_shopping
        } else if (expense.category == "entertainment") {
            imgId = R.drawable.ic_entertainment
        } else if (expense.category == "food") {
            imgId = R.drawable.ic_food
        } else if (expense.category == "transport") {
            imgId = R.drawable.ic_transport
        }
        Glide.with(ivSingleTransactionCategory)
            .load(ivSingleTransactionCategory.resources.getDrawable(imgId))
            .into(ivSingleTransactionCategory)
    }

    private fun getDateWithMonthName(taskDate: String): String {
        val taskDateArray = taskDate.split("-")
        var monthName = ""
        when (taskDateArray[1]) {
            "01" -> monthName = "January"
            "02" -> monthName = "February"
            "03" -> monthName = "March"
            "04" -> monthName = "April"
            "05" -> monthName = "May"
            "06" -> monthName = "June"
            "07" -> monthName = "July"
            "08" -> monthName = "August"
            "09" -> monthName = "September"
            "10" -> monthName = "October"
            "11" -> monthName = "November"
            "12" -> monthName = "December"
        }
        return taskDateArray[2] + " " + monthName + ", " + taskDateArray[0]
    }
}