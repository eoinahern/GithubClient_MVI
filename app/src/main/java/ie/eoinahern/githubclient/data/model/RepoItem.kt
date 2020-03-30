package ie.eoinahern.githubclient.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RepoItem(
    val id: Int,
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val description: String,
    val language: String?,
    @Json(name = "updated_at")
    val lastUpdate: String
)