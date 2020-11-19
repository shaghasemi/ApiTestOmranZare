package com.savana.apitestomranzare.Model

data class loginDataModel(
    val access_token: String,
    val `data`: Data,
    val message: String,
    val success: Boolean
)

data class Data(
    val created_at: String,
    val email: Any,
    val email_verified_at: Any,
    val id: Int,
    val mobile: String,
    val updated_at: String
)

data class loginUserInput (
    val mobile: String,
    val password: String
)