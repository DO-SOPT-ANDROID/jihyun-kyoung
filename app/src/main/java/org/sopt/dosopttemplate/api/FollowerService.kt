package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.data.model.ResponseGetFollowerDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FollowerService {
    @GET("/api/users?page=2")
    fun getFollowerList(): Response<ResponseGetFollowerDto>
}