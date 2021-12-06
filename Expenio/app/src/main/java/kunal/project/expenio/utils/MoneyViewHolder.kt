package kunal.project.expenio.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kunal.project.expenio.R
import kunal.project.expenio.models.local.Expense

class MoneyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val mTvTransactionAmount: TextView = itemView.findViewById(R.id.tvTransactionAmount)
    val mTvTransactionTitle: TextView = itemView.findViewById(R.id.tvTransactionTitle)
    val mTvTransactionDate: TextView = itemView.findViewById(R.id.tvTransactionDate)
    val mIvTransactionCategory: ImageView = itemView.findViewById(R.id.ivTransactionCategory)

    fun setData(expense: Expense) {
        mTvTransactionTitle.text = expense.title
        mTvTransactionDate.text = expense.date
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


}