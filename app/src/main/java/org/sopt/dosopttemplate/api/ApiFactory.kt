package org.sopt.dosopttemplate.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.BuildConfig
import retrofit2.Retrofit

object ApiFactory {
    const val BASE_URL = BuildConfig.AUTH_BASE_URL
    const val REQRES_BASE_URL = BuildConfig.REQRES_BASE_URL

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
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
    inline fun <reified T> createReqres(): T = reqresRetrofit.create<T>(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val reqresService = ApiFactory.createReqres<FollowerService>()
}

