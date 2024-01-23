package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.data.model.RequestLoginDto
import org.sopt.dosopttemplate.data.model.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.ResponseLoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members/sign-in")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<ResponseLoginDto>

    @POST("api/v1/members")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<Unit>
}


