package kunal.project.expenio.utils

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kunal.project.expenio.R
import kunal.project.expenio.models.local.Expense
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MoneyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val mTvTransactionAmount: TextView = itemView.findViewById(R.id.tvTransactionAmount)
    val mTvTransactionTitle: TextView = itemView.findViewById(R.id.tvTransactionTitle)
    val mTvTransactionDate: TextView = itemView.findViewById(R.id.tvTransactionDate)
    val mIvTransactionCategory: ImageView = itemView.findViewById(R.id.ivTransactionCategory)

    fun setData(expense: Expense) {
        mTvTransactionTitle.text = expense.title
        mTvTransactionDate.text = getDateWithMonthName(expense.date)
        if (expense.type == "income") {
            mTvTransactionAmount.text = "₹${expense.amount}"
        } else {
            mTvTransactionAmount.text = "-₹${expense.amount}"
        }
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
        Glide.with(mIvTransactionCategory)
            .load(mIvTransactionCategory.resources.getDrawable(imgId)).into(mIvTransactionCategory)
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