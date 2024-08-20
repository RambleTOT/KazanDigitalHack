package ramble.sokol.residentardoftatarstan.data.model

import com.google.gson.annotations.SerializedName

data class GetEventsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("beginningAt")
    val beginningAt: String?,
    @SerializedName("endingAt")
    val endingAt: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("category")
    val category: String?,
)