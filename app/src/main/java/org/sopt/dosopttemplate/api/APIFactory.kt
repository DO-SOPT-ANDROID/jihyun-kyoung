package org.sopt.dosopttemplate.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    lateinit var url: String
    private fun getLogOkHttpClient(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Retrofit2", "CONNECTION INFO -> $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLogOkHttpClient())
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(url: String): T {
        this.url = url
        return retrofit.create<T>(T::class.java)
    }
}

object ServicePool {
    private const val BASE_URL = BuildConfig.AUTH_BASE_URL
    private const val REQRES_BASE_URL = BuildConfig.REQRES_BASE_URL

    val authService = ApiFactory.create<AuthService>(BASE_URL)
    val followerService = ApiFactory.create<FollowerService>(REQRES_BASE_URL)
}


