package com.flatwater.ttwwi.auth

import retrofit2.http.Url

data class UserModel(
    val accessToken : String ?= null
)

data class LoginBackendResponse (
    val name : String,
    val email : String,
    val image : Url,

    val accessToken : String,
    val refreshToken : String
)
