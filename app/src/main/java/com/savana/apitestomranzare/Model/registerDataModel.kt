package com.savana.apitestomranzare.Model

data class registerDataModel(
    val access_token: String,
    val message: String,
    val user: User
)

data class User(
    val created_at: String,
    val id: Int,
    val mobile: String,
    val updated_at: String
)

data class registerUserInput(
    val mobile: String,
    val password: String,
    val password_confirmation: String
)