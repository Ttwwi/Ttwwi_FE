package com.flatwater.ttwwi.auth

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/oauth2/callback/kakao")

    fun postAccessToken(
        //@Header("access_token") token : String
        @Body jsonParams : UserModel
    ) : Call<LoginBackendResponse>

    companion object {
        private val gson = GsonBuilder().setLenient().create()
        fun loginRetrofit() : LoginService {
            return Retrofit.Builder()
            .baseUrl("http://43.202.39.197:8000")
            // .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(LoginService::class.java)
        }
    }
}