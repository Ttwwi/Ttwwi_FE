package com.flatwater.ttwwi.auth

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

// Interface : 어떤 parameter와 함께 어떤 메소드로 요청을 보내고, 어떤 형태로 응답받을 것인지 정의
interface LoginService {

    // "Base_uri/login/oauth2/code/kakao"와 POST 통신 -> BODY에  UserModel 형식을 갖는 jsonParams 전송
    // @Headers("accept: application/json", "content-type: application/json")
    @POST("/oauth2/authorize/kakao") // 서버 전송 주소 (수정 가능!)
    fun postAccessToken(
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