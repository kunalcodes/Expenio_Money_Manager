package kunal.project.expenio.models.remote.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("__v")
	val V: Int? = null
)