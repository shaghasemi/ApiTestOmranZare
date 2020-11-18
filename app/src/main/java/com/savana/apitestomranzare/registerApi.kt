package com.savana.apitestomranzare

import com.savana.apitestomranzare.Model.registerDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface registerApi {
    @POST("register")

    fun createUser(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String
    ): Call<registerDataModel>

    //    fun registerUser(): Call<registerDataModel>
}