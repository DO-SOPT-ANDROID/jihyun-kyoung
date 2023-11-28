package org.sopt.dosopttemplate.api

sealed class SignupResponse {
    data class ResponseSignUpDto(
        val location: String
    )
}