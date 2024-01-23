package org.sopt.dosopttemplate.data.model

sealed class SignupResponse {
    data class ResponseSignUpDto(
        val location: String
    )
}