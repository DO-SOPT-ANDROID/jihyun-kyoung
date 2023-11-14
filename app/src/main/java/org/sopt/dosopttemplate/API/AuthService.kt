package org.sopt.dosopttemplate.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>

    @POST("api/v1/members")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<Unit>
}


