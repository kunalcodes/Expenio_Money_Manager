package kunal.project.expenio.models.remote.requests

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignupRequestModel(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("age")
	val age: Int? = null
)