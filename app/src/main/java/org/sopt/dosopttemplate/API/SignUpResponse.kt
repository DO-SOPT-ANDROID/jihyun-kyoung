package org.sopt.dosopttemplate.API

sealed class SignupResponse {
    data class ResponseSignUpDto(
        val location: String
    )
}