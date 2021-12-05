package kunal.project.expenio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kunal.project.expenio.repository.ExpenseRepo

class MoneyViewModelFactory(val repo: ExpenseRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoneyViewModel(repo) as T
    }

}