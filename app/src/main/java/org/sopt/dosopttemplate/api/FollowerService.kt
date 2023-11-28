package org.sopt.dosopttemplate.api

import org.sopt.dosopttemplate.BuildConfig
import retrofit2.Call
import retrofit2.http.GET

private const val REQURES_BASE_URL =  BuildConfig.REQRES_BASE_URL

interface FollowerService {
    @GET(REQURES_BASE_URL)
    fun getFollowerList(): Call<ResponseGetFollwerDto>
}