package com.savana.apitestomranzare

import com.savana.apitestomranzare.Model.loginDataModel
import com.savana.apitestomranzare.Model.registerDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface labApi {

    @FormUrlEncoded

    @POST("register")
    fun registerUser(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String
    ): Call<registerDataModel>

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
    ): Call<loginDataModel>

}