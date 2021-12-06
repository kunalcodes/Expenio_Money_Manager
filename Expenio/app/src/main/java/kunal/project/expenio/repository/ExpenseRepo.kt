package kunal.project.expenio.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kunal.project.expenio.models.remote.Resource
import kunal.project.expenio.models.remote.ResponseHandler
import kunal.project.expenio.models.remote.MoneyAPI
import kunal.project.expenio.models.remote.requests.CreateExpenseRequestModel
import kunal.project.expenio.models.remote.requests.LoginRequestModel
import kunal.project.expenio.models.remote.responses.LoginResponse
import kunal.project.expenio.models.remote.requests.SignupRequestModel
import kunal.project.expenio.models.remote.responses.GetExpenseResponseModel
import kunal.project.expenio.models.remote.responses.SignupResponseModel
import kotlinx.coroutines.*
import kunal.project.expenio.models.local.Expense
import kunal.project.expenio.models.local.ExpenseDAO
import kunal.project.expenio.models.remote.Network
import java.lang.Exception
import javax.inject.Inject

class ExpenseRepo @Inject constructor(val expenseDAO: ExpenseDAO, val api: MoneyAPI) {

    private val responseHandler = ResponseHandler()

    suspend fun login(loginRequestModel: LoginRequestModel): Resource<LoginResponse> {
        return try {
            val response = api.login(loginRequestModel)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun signup(signupRequestModel: SignupRequestModel): Resource<SignupResponseModel> {
        return try {
            val response = api.signup(signupRequestModel)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun getAllTransactionsFromAPI(token : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getAllTransactionsFromAPI(token)
            saveTransactionsToDB(response)
        }
    }

    private fun saveTransactionsToDB(response: GetExpenseResponseModel) {
        val listOfTransactions = ArrayList<Expense>()
        response.forEach {
            val date = it.createdAt
            val desc = it.description.split("$#*#$")
            val newTransaction = Expense(it.title, desc[0], it.status, date, desc[1], desc[2], it._id)
            listOfTransactions.add(newTransaction)
        }
        expenseDAO.deleteAllTransactionsFromDB()
        expenseDAO.addAllTransactionsToDB(listOfTransactions)
    }

    fun addNewTransaction(token: String, createExpenseRequestModel : CreateExpenseRequestModel){
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.addTransactionToAPI(token, createExpenseRequestModel)
            val date = response.createdAt
            val desc = response.description!!.split("*#*#*")
            val newTransaction = Expense(response.title!!, desc[0], response.status!!, date!!, desc[1], desc[2], response.id!!)
            expenseDAO.addTransactionToDB(newTransaction)
        }
    }


    fun getAllTransactions(): LiveData<List<Expense>> {
        return expenseDAO.getAllTransactionsFromDB()
    }


    fun deleteTransaction(token: String, expense: Expense) {
        CoroutineScope(Dispatchers.IO).launch {
            api.deleteTransactionFromAPI(token, expense.id!!)
            expenseDAO.deleteTransactionFromDB(expense)
        }
    }
}