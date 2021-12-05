package kunal.project.expenio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kunal.project.expenio.models.remote.Resource
import kunal.project.expenio.models.remote.requests.CreateExpenseRequestModel
import kunal.project.expenio.models.remote.requests.LoginRequestModel
import kunal.project.expenio.models.remote.responses.LoginResponse
import kunal.project.expenio.models.remote.requests.SignupRequestModel
import kunal.project.expenio.models.remote.responses.SignupResponseModel
import kunal.project.expenio.repository.ExpenseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MoneyViewModel @Inject constructor(val repo: ExpenseRepo) : ViewModel() {

    fun userLogin(loginRequestModel: LoginRequestModel): LiveData<Resource<LoginResponse>>{

        return liveData(Dispatchers.IO) {
            val result = repo.login(loginRequestModel)
            emit(result)
        }
    }

    fun userSignup(signupRequestModel: SignupRequestModel): LiveData<Resource<SignupResponseModel>>{

        return liveData(Dispatchers.IO) {
            val result = repo.signup(signupRequestModel)
            emit(result)
        }
    }

    fun getTasksFromAPI(){
        repo.getRemoteTasks()
    }

    fun createNewTask(createExpenseRequestModel: CreateExpenseRequestModel){
        repo.createTask(createExpenseRequestModel)
    }
//
//    fun addTask(task: Task){
//        repo.addTaskToRoom(task)
//    }
//
//    fun getTasksFromDB(): LiveData<List<Task>> {
//        return repo.getAllTasks()
//    }
//
//    fun updateTask(task: Task){
//        repo.updateTask(task)
//    }
//
//    fun deleteTask(task: Task){
//        repo.deleteTask(task)
//    }
}