package org.sopt.dosopttemplate.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetFollwerDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<ResponseData>,
    @SerialName("support")
    val support: ResponseSupport
)

@Serializable
data class ResponseSupport(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String
)

@Serializable
data class ResponseData(
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
