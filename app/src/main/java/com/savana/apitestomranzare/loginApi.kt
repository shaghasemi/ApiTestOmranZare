package com.savana.apitestomranzare

import com.savana.apitestomranzare.Model.LoginDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface loginApi {
    @FormUrlEncoded
    @POST("login")

    fun loginUser(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
    ): Call<LoginDataModel>
}