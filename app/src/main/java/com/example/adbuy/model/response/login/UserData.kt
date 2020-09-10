package com.example.adbuy.model.response.login

import com.google.gson.annotations.SerializedName

data class UserData(

    @field:SerializedName("response")
    val response: Response? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("errors")
    val errors: Any? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class Data(

    @field:SerializedName("mobile")
    val mobile: String? = null,

    @field:SerializedName("last_name")
    val lastName: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_name")
    val firstName: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("token")
    val token: String? = null
)

data class Response(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("meta")
    val meta: Any? = null
)