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
    private var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRjYzk1NGYwNDg1NDBiNWMxNDkzNzciLCJpYXQiOjE2MzI0MjU0ODJ9.ibPc3YVmAwXyNUKImsB-p_aTtrXwymJG6VL3Z0QuNJ8"

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

    fun getRemoteTasks() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getExpenseFromAPI(token!!)
            saveToDB(response)
        }
    }

    fun createTask(createExpenseRequestModel : CreateExpenseRequestModel){
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.createExpense(token!!, createExpenseRequestModel)
            val newTask = Expense(response.title!!, response.description!!, 0)
            expenseDAO.addTask(newTask)
        }
    }

    private fun saveToDB(response: GetExpenseResponseModel) {

        val listOfTasks = ArrayList<Expense>()
        response.forEach {
            val newTask = Expense(it.title, it.description, 0)
            listOfTasks.add(newTask)
        }
        expenseDAO.deleteAll()
        expenseDAO.addTasks(listOfTasks)
    }

    fun addTaskToRoom(task: Expense) {
        CoroutineScope(Dispatchers.IO).launch {
            expenseDAO.addTask(task)
        }
    }

    fun getAllTasks(): LiveData<List<Expense>> {
        return expenseDAO.getTasks()
    }

    fun updateTask(task: Expense) {
        CoroutineScope(Dispatchers.IO).launch {
            expenseDAO.updateTask(task)
        }
    }

    fun deleteTask(task: Expense) {
        CoroutineScope(Dispatchers.IO).launch {
            expenseDAO.delete(task)
        }
    }
}