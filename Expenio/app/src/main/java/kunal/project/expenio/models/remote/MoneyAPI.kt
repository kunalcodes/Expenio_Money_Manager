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

    @POST("users/login")
    suspend fun login(
        @Body loginRequest: LoginRequestModel
    ): LoginResponse


    @POST("users/")
    suspend fun signup(
        @Body signupRequest: SignupRequestModel
    ): SignupResponseModel


    @POST("tasks")
    suspend fun addTransactionToAPI(
        @Header("Authorization") token : String,
        @Body createExpenseRequestModel: CreateExpenseRequestModel
    ): CreateExpenseResponseModel

    @GET("tasks")
    suspend fun getAllTransactionsFromAPI(
        @Header("Authorization") token : String
    ) : GetExpenseResponseModel

    @DELETE("tasks/{id}")
    suspend fun deleteTransactionFromAPI(
        @Header("Authorization") token : String,
        @Path("id") id : String
    )

    @POST("users/logout")
    suspend fun logout(
        @Header("Authorization") token : String,
    )

    @DELETE("users/me")
    suspend fun deleteUserFromAPI(
        @Header("Authorization") token : String,
    )
}