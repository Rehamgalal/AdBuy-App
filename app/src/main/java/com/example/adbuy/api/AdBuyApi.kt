package com.example.adbuy.api

import android.provider.ContactsContract
import com.example.adbuy.model.UserData
import com.example.adbuy.other.Resource
import retrofit2.Response
import retrofit2.http.*

interface AdBuyApi {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("email")  email:String,@Field("password") password:String): Response<UserData>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun signup(@Field("first_name") firstName:String, @Field("last_name")lastName:String,@Field("email")  email:String,
                       @Field("password") password:String,@Field("c_password")confirmPassword:String,@Field("mobile")mobile:String): Response<UserData>
}