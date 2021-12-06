package kunal.project.expenio.utils

import kunal.project.expenio.models.local.Expense

interface ItemClickListener {
    fun onItemClicked(expense: Expense)
    fun onItemLonClicked(expense: Expense)
}