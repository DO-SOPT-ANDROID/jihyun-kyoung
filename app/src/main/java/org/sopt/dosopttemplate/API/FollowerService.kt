package org.sopt.dosopttemplate.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface FollowerService {
    @GET("https://reqres.in/api/users?page=2")
    fun getFollowerList(): Call<ResponseGetFollwerDto>
}