package kunal.project.expenio.models.remote.responses

import com.google.gson.annotations.SerializedName

data class SignupResponseModel(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null
)