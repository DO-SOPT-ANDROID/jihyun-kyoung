package org.sopt.dosopttemplate.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.model.Profile

@Serializable
data class ResponseGetFollowerDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<Follower>,
    @SerialName("support")
    val support: ResponseSupport
) {

    @Serializable
    data class ResponseSupport(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String
    )

    @Serializable
    data class Follower(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        @SerialName("avatar")
        val avatar: String,
    )

    fun toProfiles(): List<Profile> {
        return data.map {
            Profile(
                avatar = it.avatar,
                email = it.email,
                intId = it.id,
                nickname = it.firstName,
                type = Profile.FRIEND,
                id = it.firstName + " " + it.lastName,
            )
        }
    }
}