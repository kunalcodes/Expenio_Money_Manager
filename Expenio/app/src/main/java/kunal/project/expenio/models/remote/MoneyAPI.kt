package kunal.project.expenio.models.remote

import kunal.project.expenio.models.remote.requests.CreateExpenseRequestModel
import kunal.project.expenio.models.remote.requests.LoginRequestModel
import kunal.project.expenio.models.remote.responses.LoginResponse
import kunal.project.expenio.models.remote.requests.SignupRequestModel
import kunal.project.expenio.models.remote.responses.CreateExpenseResponseModel
import kunal.project.expenio.models.remote.responses.GetExpenseResponseModel
import kunal.project.expenio.models.remote.responses.SignupResponseModel
import retrofit2.http.*


interface MoneyAPI {

    //@Headers("Accept: application/json")
    @POST("users/login")
    suspend fun login(
        @Body loginRequest: LoginRequestModel
    ): LoginResponse


    @POST("users/")
    suspend fun signup(
        @Body signupRequest: SignupRequestModel
    ): SignupResponseModel

    @POST("tasks")
    suspend fun createExpense(
        @Header("Authorization") token : String,
        @Body createExpenseRequestModel: CreateExpenseRequestModel
    ): CreateExpenseResponseModel

    @GET("tasks")
    suspend fun getExpenseFromAPI(
        @Header("Authorization") token : String
    ) : GetExpenseResponseModel

}