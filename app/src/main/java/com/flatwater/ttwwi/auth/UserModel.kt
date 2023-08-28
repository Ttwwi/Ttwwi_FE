package com.flatwater.ttwwi.auth

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class UserModel(
    @SerializedName("accessToken")
    val accessToken: String ?= null
)

data class LoginBackendResponse (
     // val name : String,
     // val email : String,
     val accessToken : String,
     // val refreshToken : String
)
