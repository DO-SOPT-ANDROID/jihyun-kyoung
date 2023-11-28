package org.sopt.dosopttemplate.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("password")
    val password: String,
)