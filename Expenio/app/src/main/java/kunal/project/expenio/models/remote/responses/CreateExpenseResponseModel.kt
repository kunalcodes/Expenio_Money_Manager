package kunal.project.expenio.models.remote.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreateExpenseResponseModel(

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("__v")
	val V: Int? = null
)