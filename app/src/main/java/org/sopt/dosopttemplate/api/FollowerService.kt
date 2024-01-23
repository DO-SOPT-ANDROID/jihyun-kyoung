package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.data.model.ResponseGetFollwerDto
import retrofit2.Call
import retrofit2.http.GET

interface FollowerService {
    @GET("/api/users?page=2")
    fun getFollowerList(): Call<ResponseGetFollwerDto>
}